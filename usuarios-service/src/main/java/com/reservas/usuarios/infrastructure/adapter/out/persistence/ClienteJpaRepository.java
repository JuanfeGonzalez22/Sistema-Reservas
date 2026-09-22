package com.reservas.usuarios.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioId(Long usuarioId);
}
