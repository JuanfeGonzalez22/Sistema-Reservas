package com.reservas.pagos.application.service;

import com.reservas.pagos.domain.exception.PagoNoEncontradoException;
import com.reservas.pagos.domain.model.EstadoPago;
import com.reservas.pagos.domain.model.Pago;
import com.reservas.pagos.domain.model.ResultadoCobro;
import com.reservas.pagos.domain.port.in.PagoUseCase;
import com.reservas.pagos.domain.port.out.EventoPublisherPort;
import com.reservas.pagos.domain.port.out.PagoRepositoryPort;
import com.reservas.pagos.domain.port.out.PasarelaPagoPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PagoService implements PagoUseCase {

    private final PagoRepositoryPort pagoRepositoryPort;
    private final PasarelaPagoPort pasarelaPagoPort;
    private final EventoPublisherPort eventoPublisherPort;

    public PagoService(PagoRepositoryPort pagoRepositoryPort,
                       PasarelaPagoPort pasarelaPagoPort,
                       EventoPublisherPort eventoPublisherPort) {
        this.pagoRepositoryPort = pagoRepositoryPort;
        this.pasarelaPagoPort = pasarelaPagoPort;
        this.eventoPublisherPort = eventoPublisherPort;
    }

    @Override
    public Pago procesarPago(Long reservaId, Long clienteId, BigDecimal monto) {

        Optional<Pago> existente = pagoRepositoryPort.buscarPorReservaId(reservaId);
        if (existente.isPresent()) {
            return existente.get();
        }

        ResultadoCobro resultado = pasarelaPagoPort.cobrar(monto);
        EstadoPago estado = resultado.aprobado()
                ? EstadoPago.APROBADO
                : EstadoPago.RECHAZADO;

        Pago pago = new Pago(reservaId, clienteId, monto, estado, resultado.motivo());
        Pago guardado = pagoRepositoryPort.guardar(pago);

        if(resultado.aprobado()){
            eventoPublisherPort.publicarPagoAprobado(reservaId, guardado.getId());
        } else {
            eventoPublisherPort.publicarPagoRechazado(reservaId, resultado.motivo());
        }


        return guardado;
    }

    @Override
    @Transactional(readOnly = true)
    public Pago consultarPorReservaId(Long reservaId) {
        return pagoRepositoryPort.buscarPorReservaId(reservaId)
                .orElseThrow(() -> new PagoNoEncontradoException(reservaId));
    }
}
