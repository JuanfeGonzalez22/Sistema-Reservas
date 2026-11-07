package com.reservas.pagos.domain.port.in;

import com.reservas.pagos.domain.model.Pago;

import java.math.BigDecimal;

public interface PagoUseCase {
    Pago procesarPago(Long reservaId, Long clienteId, BigDecimal monto);
    Pago consultarPorReservaId(Long reservaId);
}
