package com.reservas.auth.domain.model;

public class Usuario {

    private Long id;
    private String email;
    private String password;
    private Rol rol;
    private String nombre;
    private boolean activo;

    public Usuario() {
    }

    public Usuario(Long id, String email, String password, Rol rol, String nombre) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.nombre = nombre;
        this.activo = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}