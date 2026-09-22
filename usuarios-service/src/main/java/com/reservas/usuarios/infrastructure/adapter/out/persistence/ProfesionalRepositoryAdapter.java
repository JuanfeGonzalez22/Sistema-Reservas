package com.reservas.usuarios.infrastructure.adapter.out.persistence;

import com.reservas.usuarios.domain.model.Profesional;
import com.reservas.usuarios.domain.port.out.ProfesionalRepositoryPort;

import java.util.Optional;

public class ProfesionalRepositoryAdapter implements ProfesionalRepositoryPort {

    private ProfesionalJpaRepository profesionalJpaRepository;

    public ProfesionalRepositoryAdapter(ProfesionalJpaRepository profesionalJpaRepository) {
        this.profesionalJpaRepository = profesionalJpaRepository;
    }


    @Override
    public Profesional guardar(Profesional profesional) {
        ProfesionalEntity entity = profesionalJpaRepository.findByUsuarioID(profesional.getUsuarioId())
                .map(existing -> {
                    existing.setEspecialidad(profesional.getEspecialidad());
                    existing.setHorarioTrabajo(profesional.getHorarioTrabajo());
                    return existing;
                })
                .orElse(new ProfesionalEntity(null, profesional.getUsuarioId(), profesional.getEspecialidad(),  profesional.getHorarioTrabajo()));
        ProfesionalEntity guardada = profesionalJpaRepository.save(entity);
        return toDomain(guardada);
    }

    @Override
    public Optional<Profesional> buscarPorId(Long usuarioId) {
        return profesionalJpaRepository.findByUsuarioID(usuarioId).map(this::toDomain);
    }

    @Override
    public boolean existePorId(Long usuarioId) {
        return profesionalJpaRepository.existsByUsuarioID(usuarioId);
    }

    private Profesional toDomain(ProfesionalEntity entity) {
        return new Profesional(entity.getUsuarioId(), entity.getEspecialidad(), entity.getHorarioTrabajo());
    }
}
