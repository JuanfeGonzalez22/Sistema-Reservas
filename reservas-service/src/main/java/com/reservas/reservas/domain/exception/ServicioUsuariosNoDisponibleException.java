package com.reservas.reservas.domain.exception;

public class ServicioUsuariosNoDisponibleException extends RuntimeException {

    public ServicioUsuariosNoDisponibleException(Throwable cause) {
        super("El servicio de usuarios no está disponible", cause);
    }

}
