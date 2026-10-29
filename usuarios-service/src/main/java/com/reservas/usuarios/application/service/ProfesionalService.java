package com.reservas.usuarios.application.service;

import com.reservas.usuarios.domain.exception.ProfesionalNoEncontradoException;
import com.reservas.usuarios.domain.model.Profesional;
import com.reservas.usuarios.domain.port.in.ProfesionalUseCase;
import com.reservas.usuarios.domain.port.out.ProfesionalRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProfesionalService  implements ProfesionalUseCase {

    private final ProfesionalRepositoryPort profesionalRepositoryPort;

    public ProfesionalService(ProfesionalRepositoryPort profesionalRepositoryPort) {
        this.profesionalRepositoryPort = profesionalRepositoryPort;
    }

    @Override
    public Profesional crearPerfil(Long usuarioId, String especialidad, String horarioTrabajo, BigDecimal tarifa) {
        if (profesionalRepositoryPort.existePorId(usuarioId)) {
            throw new ProfesionalNoEncontradoException(usuarioId);
        }
        Profesional profesional = new Profesional(usuarioId, especialidad, horarioTrabajo,tarifa );
        return profesionalRepositoryPort.guardar(profesional);
    }

    @Override
    public Profesional obtenerPerfil(Long usuarioId) {
        return profesionalRepositoryPort.buscarPorId(usuarioId)
                .orElseThrow(() -> new ProfesionalNoEncontradoException(usuarioId));
    }

    @Override
    public Profesional actualizarPerfil(Long usuarioId, String especialidad, String horarioTrabajo, BigDecimal tarifa) {
        Profesional profesional = obtenerPerfil(usuarioId);
        profesional.setEspecialidad(especialidad);
        profesional.setHorarioTrabajo(horarioTrabajo);
        profesional.setTarifa(tarifa);
        return profesionalRepositoryPort.guardar(profesional);
    }
}
