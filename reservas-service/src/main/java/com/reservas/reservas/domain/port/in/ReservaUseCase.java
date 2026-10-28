package com.reservas.reservas.domain.port.in;

import com.reservas.reservas.domain.model.EstadoReserva;
import com.reservas.reservas.domain.model.Reserva;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaUseCase {

    Reserva crearReserva(Long clienteId, Long profesionalId, LocalDateTime fechaHoraInicio,
                         LocalDateTime fechaHoraFin, String notas);

    Reserva confirmarReserva(Long reservaId, Long usuarioId);

    Reserva cancelarReserva(Long reservaId, Long usuarioId);

    Reserva completarReserva(Long reservaId, Long usuarioId);

    Reserva obtenerReserva(Long reservaId, Long usuarioId);

    List<Reserva> listarReservaPorCliente(Long clienteId);

    List<Reserva> listarReservaPorProfesional(Long profesionalId);
}
