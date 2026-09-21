package com.reservas.usuarios.domain.model;


public class Profesional {

    private Long usuarioId;
    private String especialidad;
    private String horarioTrabajo;

    public Profesional(Long usuarioId, String especialidad, String horarioTrabajo){
        this.usuarioId = usuarioId;
        this.especialidad = especialidad;
        this.horarioTrabajo = horarioTrabajo;
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
}
