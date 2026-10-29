package com.reservas.reservas.domain.exception;

public class ProfesionalNoEncontradoException extends RuntimeException {

    public ProfesionalNoEncontradoException(Long profesionalId) {
        super("Profesional no encontrado: " + profesionalId);
    }

}
