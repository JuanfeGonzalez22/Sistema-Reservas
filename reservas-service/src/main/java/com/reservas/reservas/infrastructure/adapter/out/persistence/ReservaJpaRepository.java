package com.reservas.reservas.infrastructure.adapter.out.persistence;

import com.reservas.reservas.domain.model.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaJpaRepository extends JpaRepository<ReservaEntity, Long> {

    List<ReservaEntity> findByClienteId(Long id);

    List<ReservaEntity> findByProfesionalId(Long id);

    @Query(value = "SELECT pg_advisory_xact_lock(:profesionalId)", nativeQuery = true)
    void bloquearAgenda(@Param("profesionalId") Long profesionalId);

    @Query("""
           SELECT COUNT(r) > 0 FROM ReservaEntity r
           WHERE r.profesionalId = :profesionalId
             AND r.estado <> :estadoExcluido
             AND r.fechaHoraInicio < :fechaHoraFin
             AND r.fechaHoraFin > :fechaHoraInicio
           """)
    boolean existeSolapamiento(@Param("profesionalId") Long profesionalId,
                               @Param("fechaHoraInicio") LocalDateTime fechaHoraInicio,
                               @Param("fechaHoraFin") LocalDateTime fechaHoraFin,
                               @Param("estadoExcluido") EstadoReserva estadoExcluido);

}
