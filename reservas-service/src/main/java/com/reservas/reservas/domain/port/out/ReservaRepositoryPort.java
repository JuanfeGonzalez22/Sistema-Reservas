package com.reservas.reservas.domain.port.out;

import com.reservas.reservas.domain.model.Reserva;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservaRepositoryPort {

    Reserva guardar(Reserva reserva);

    Optional<Reserva> buscarReservaPorId(Long id);

    boolean existeSolapamiento(Long profesionalId, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin);

    List<Reserva> listarReservaPorClienteId(Long clienteId);

    List<Reserva> listarReservasPorProfesionalId(Long profesionalId);

    void bloquearAgenda(Long profesionalId);

}
