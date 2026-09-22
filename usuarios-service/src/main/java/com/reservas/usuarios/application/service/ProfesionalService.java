package com.reservas.usuarios.application.service;

import com.reservas.usuarios.domain.model.Profesional;
import com.reservas.usuarios.domain.port.in.ProfesionalUseCase;
import com.reservas.usuarios.domain.port.out.ProfesionalRepositoryPort;

public class ProfesionalService  implements ProfesionalUseCase {

    private final ProfesionalRepositoryPort profesionalRepositoryPort;

    public ProfesionalService(ProfesionalRepositoryPort profesionalRepositoryPort) {
        this.profesionalRepositoryPort = profesionalRepositoryPort;
    }

    @Override
    public Profesional crearPerfil(Long usuarioId, String especialidad, String horarioTrabajo) {
        if (profesionalRepositoryPort.existePorId(usuarioId)) {
            throw new RuntimeException("Profesional ya existe");
        }
        Profesional profesional = new Profesional(usuarioId, especialidad, horarioTrabajo);
        return profesionalRepositoryPort.guardar(profesional);
    }

    @Override
    public Profesional obtenerPerfil(Long usuarioId) {
        return profesionalRepositoryPort.buscarPorId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));
    }

    @Override
    public Profesional actualizarPerfil(Long usuarioId, String especialidad, String horarioTrabajo) {
        Profesional profesional = obtenerPerfil(usuarioId);
        profesional.setEspecialidad(especialidad);
        profesional.setHorarioTrabajo(horarioTrabajo);
        return profesionalRepositoryPort.guardar(profesional);
    }
}
