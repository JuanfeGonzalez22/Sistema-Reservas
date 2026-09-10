package com.reservas.auth.domain.model;

public class ResultadoAutenticacion {

    private final Usuario usuario;
    private final String token;

    public ResultadoAutenticacion(Usuario usuario, String token) {
        this.usuario = usuario;
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public String getToken() {
        return token;
    }

}
