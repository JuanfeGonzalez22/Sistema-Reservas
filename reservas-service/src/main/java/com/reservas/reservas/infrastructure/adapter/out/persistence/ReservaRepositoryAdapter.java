package com.reservas.reservas.infrastructure.adapter.out.persistence;

import com.reservas.reservas.domain.model.EstadoReserva;
import com.reservas.reservas.domain.model.Reserva;
import com.reservas.reservas.domain.port.out.ReservaRepositoryPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ReservaRepositoryAdapter implements ReservaRepositoryPort {

    private final ReservaJpaRepository reservaJpaRepository;

    public ReservaRepositoryAdapter(ReservaJpaRepository reservaJpaRepository) {
        this.reservaJpaRepository = reservaJpaRepository;
    }

    @Override
    public Reserva guardar(Reserva reserva) {
        ReservaEntity entity = toEntity(reserva);
        ReservaEntity guardada = reservaJpaRepository.save(entity);
        return toDomain(guardada);
    }

    @Override
    public Optional<Reserva> buscarReservaPorId(Long id) {
        return reservaJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public boolean existeSolapamiento(Long profesionalId, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        return reservaJpaRepository.existeSolapamiento(profesionalId, fechaHoraInicio, fechaHoraFin, EstadoReserva.CANCELADA);
    }

    @Override
    public List<Reserva> listarReservaPorClienteId(Long clienteId) {
        return reservaJpaRepository.findByClienteId(clienteId).stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Reserva> listarReservasPorProfesionalId(Long profesionalId) {
        return reservaJpaRepository.findByProfesionalId(profesionalId).stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void bloquearAgenda(Long profesionalId) {
        reservaJpaRepository.bloquearAgenda(profesionalId);
    }

    private ReservaEntity toEntity(Reserva reserva) {
        ReservaEntity entity = new ReservaEntity();
        entity.setId(reserva.getId());
        entity.setClienteId(reserva.getClienteId());
        entity.setProfesionalId(reserva.getProfesionalId());
        entity.setFechaHoraInicio(reserva.getFechaHoraInicio());
        entity.setFechaHoraFin(reserva.getFechaHoraFin());
        entity.setEstado(reserva.getEstado());
        entity.setFechaCreacion(reserva.getFechaCreacion());
        entity.setFechaActualizacion(reserva.getFechaActualizacion());
        entity.setNotas(reserva.getNotas());
        entity.setPrecio(reserva.getPrecio());
        return entity;
    }

    private Reserva toDomain(ReservaEntity entity) {
        Reserva reserva = new Reserva(
                entity.getClienteId(),
                entity.getProfesionalId(),
                entity.getFechaHoraInicio(),
                entity.getFechaHoraFin(),
                entity.getNotas(),
                entity.getPrecio()
        );
        reserva.setId(entity.getId());
        reserva.setEstado(entity.getEstado());
        reserva.setFechaCreacion(entity.getFechaCreacion());
        reserva.setFechaActualizacion(entity.getFechaActualizacion());
        return reserva;
    }
}
