package com.reservas.reservas.domain.exception;

public class ReservaNoEncontradaException extends RuntimeException {

    public ReservaNoEncontradaException(Long reservaId) {
        super("Reserva no encontrada: " + reservaId);
    }

}
