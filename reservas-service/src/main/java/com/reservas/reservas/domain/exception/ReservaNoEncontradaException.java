package com.reservas.reservas.domain;

import com.reservas.reservas.domain.model.Reserva;

public class ReservaNoEncontradaException extends RuntimeException {

    public ReservaNoEncontradaException(Long reservaId) {
        super("Reserva no encontrada" + reservaId);
    }

}
