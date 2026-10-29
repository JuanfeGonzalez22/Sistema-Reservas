package com.reservas.usuarios.domain.port.in;

import com.reservas.usuarios.domain.model.Profesional;

import java.math.BigDecimal;

public interface ProfesionalUseCase {
    Profesional crearPerfil(Long usuarioId, String especialidad, String horarioTrabajo, BigDecimal tarifa);
    Profesional obtenerPerfil(Long usuarioId);
    Profesional actualizarPerfil(Long usuarioId, String especialidad, String horarioTrabajo, BigDecimal tarifa);

}
