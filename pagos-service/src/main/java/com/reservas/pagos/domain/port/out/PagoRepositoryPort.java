package com.reservas.pagos.domain.port.out;

import com.reservas.pagos.domain.model.Pago;

import java.util.Optional;

public interface PagoRepositoryPort {
    Pago guardar(Pago pago);
    Optional<Pago> buscarPorId(Long id);
    Optional<Pago> buscarPorReservaId(Long reservaId);
}
