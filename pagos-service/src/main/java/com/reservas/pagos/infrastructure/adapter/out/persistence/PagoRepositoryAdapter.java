package com.reservas.pagos.infrastructure.adapter.out.persistence;

import com.reservas.pagos.domain.model.Pago;
import com.reservas.pagos.domain.port.out.PagoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PagoRepositoryAdapter implements PagoRepositoryPort {

    private PagoJpaRepository pagoJpaRepository;

    public PagoRepositoryAdapter(PagoJpaRepository pagoJpaRepository) {
        this.pagoJpaRepository = pagoJpaRepository;
    }

    @Override
    public Pago guardar(Pago pago) {
        PagoEntity entity = toEntity(pago);
        PagoEntity guardada = pagoJpaRepository.save(entity);
        return toDomain(guardada);
    }

    @Override
    public Optional<Pago> buscarPorId(Long pagoId) {
        return pagoJpaRepository.findById(pagoId).map(this::toDomain);
    }

    @Override
    public Optional<Pago> buscarPorReservaId(Long reservaId) {
        return pagoJpaRepository.findByReservaId(reservaId).map(this::toDomain);
    }

    private PagoEntity toEntity(Pago pago) {
        PagoEntity entity = new PagoEntity();
        entity.setId(pago.getId());
        entity.setReservaId(pago.getReservaId());
        entity.setClienteId(pago.getClienteId());
        entity.setMonto(pago.getMonto());
        entity.setEstado(pago.getEstado());
        entity.setMotivoRechazo(pago.getMotivoRechazo());
        entity.setFechaCreacion(pago.getFechaCreacion());
        return entity;
    }

    private Pago toDomain(PagoEntity entity){
        Pago pago = new Pago(
                entity.getReservaId(),
                entity.getClienteId(),
                entity.getMonto(),
                entity.getEstado(),
                entity.getMotivoRechazo());
        pago.setId(entity.getId());
        pago.setFechaCreacion(entity.getFechaCreacion());
        return pago;
    }
}
