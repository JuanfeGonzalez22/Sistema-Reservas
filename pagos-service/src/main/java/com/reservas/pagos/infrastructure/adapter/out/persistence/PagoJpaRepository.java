package com.reservas.pagos.infrastructure.adapter.out.persistence;

import com.reservas.pagos.domain.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagoJpaRepository extends JpaRepository<PagoEntity, Long> {
    Optional<PagoEntity> findByReservaId(Long reservaId);
}
