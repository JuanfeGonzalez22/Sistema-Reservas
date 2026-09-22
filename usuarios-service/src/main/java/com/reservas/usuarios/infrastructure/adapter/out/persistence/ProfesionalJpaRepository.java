package com.reservas.usuarios.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesionalJpaRepository extends JpaRepository<ProfesionalEntity, Long> {

    Optional<ProfesionalEntity> findByUsuarioID(Long usuarioID);
    boolean existsByUsuarioID(Long usuarioID);
}
