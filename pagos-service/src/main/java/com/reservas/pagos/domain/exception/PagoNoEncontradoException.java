package com.reservas.pagos.domain.exception;

public class PagoNoEncontradoException extends RuntimeException {
    public PagoNoEncontradoException(Long reservaId) {
        super("No existe pago para la reserva " + reservaId);
    }
}
