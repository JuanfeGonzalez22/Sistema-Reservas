package com.reservas.usuarios.domain.model;

public class Cliente {

    private Long usuarioId;
    private String telefono;
    private String direccion;

    public Cliente(Long usuarioId, String telefono, String direccion) {
        this.usuarioId = usuarioId;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
