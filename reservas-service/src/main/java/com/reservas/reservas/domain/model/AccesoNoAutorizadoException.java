package com.reservas.reservas.domain.model;

public class AccesoNoAutorizadoException extends RuntimeException{

    public AccesoNoAutorizadoException(){
        super("No tiene permisos para este reserva");
    }

}
