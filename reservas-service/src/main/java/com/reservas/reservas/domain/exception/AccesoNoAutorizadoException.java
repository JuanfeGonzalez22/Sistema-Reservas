package com.reservas.reservas.domain.exception;

public class AccesoNoAutorizadoException extends RuntimeException{

    public AccesoNoAutorizadoException(){
        super("No tiene permisos para este reserva");
    }

}
