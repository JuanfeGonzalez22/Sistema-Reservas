package com.reservas.pagos.infrastructure.adapter.in.messaging;

import com.reservas.pagos.domain.port.in.PagoUseCase;
import com.reservas.pagos.infrastructure.adapter.in.messaging.event.ReservaCreadaEvent;
import com.reservas.pagos.infrastructure.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReservaCreadaListener {

    private static final Logger log = LoggerFactory.getLogger(ReservaCreadaListener.class);
    private final PagoUseCase pagoUseCase;

    public ReservaCreadaListener(PagoUseCase pagoUseCase) {
        this.pagoUseCase = pagoUseCase;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_RESERVA_CREADA)
    public void recibir(ReservaCreadaEvent evento){
        log.info("Recibido ReservaCreada: reservaId{}, monto={}",
                evento.reservaId(),evento.monto());

        pagoUseCase.procesarPago(
                evento.reservaId(),
                evento.clienteId(),
                evento.monto()
        );
    }

}
