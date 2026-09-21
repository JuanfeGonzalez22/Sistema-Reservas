package com.reservas.usuarios.domain.port.out;

import com.reservas.usuarios.domain.model.Profesional;

import java.util.Optional;

public interface ProfesionalRepositoryPort {

    Profesional guardar(Profesional profesional);
    Optional<Profesional> buscarPorId(Long usuarioId);
    boolean existePorId(Long usuarioId);

}
