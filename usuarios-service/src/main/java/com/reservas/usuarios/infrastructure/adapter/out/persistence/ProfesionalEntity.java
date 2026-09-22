package com.reservas.usuarios.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "profesionales")
public class ProfesionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false, unique = true)
    private Long usuarioId;

    @Column
    private String especialidad;

    @Column(name = "horario_trabajo")
    private String horarioTrabajo;

    public ProfesionalEntity() {
    }

    public ProfesionalEntity(Long id, Long usuarioId, String especialidad, String horarioTrabajo) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.especialidad = especialidad;
        this.horarioTrabajo = horarioTrabajo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getHorarioTrabajo() { return horarioTrabajo; }
    public void setHorarioTrabajo(String horarioTrabajo) { this.horarioTrabajo = horarioTrabajo; }
}