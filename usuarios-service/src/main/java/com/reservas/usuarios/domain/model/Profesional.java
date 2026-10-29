package com.reservas.usuarios.domain.model;


import java.math.BigDecimal;

public class Profesional {

    private Long usuarioId;
    private String especialidad;
    private String horarioTrabajo;
    private BigDecimal tarifa;

    public Profesional(Long usuarioId, String especialidad, String horarioTrabajo, BigDecimal tarifa) {
        this.usuarioId = usuarioId;
        this.especialidad = especialidad;
        this.horarioTrabajo = horarioTrabajo;
        this.tarifa = tarifa;

    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getHorarioTrabajo() {
        return horarioTrabajo;
    }

    public void setHorarioTrabajo(String horarioTrabajo) {
        this.horarioTrabajo = horarioTrabajo;
    }

    public BigDecimal getTarifa() {return tarifa;}

    public void setTarifa(BigDecimal tarifa) {this.tarifa = tarifa;}
}
