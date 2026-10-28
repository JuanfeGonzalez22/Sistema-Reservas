package com.reservas.reservas.application.service;

import com.reservas.reservas.domain.ReservaNoEncontradaException;
import com.reservas.reservas.domain.model.AccesoNoAutorizadoException;
import com.reservas.reservas.domain.model.EstadoReserva;
import com.reservas.reservas.domain.model.Reserva;
import com.reservas.reservas.domain.port.in.ReservaUseCase;
import com.reservas.reservas.domain.port.out.ReservaRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService implements ReservaUseCase {

    private final ReservaRepositoryPort reservaRepositoryPort;

    public ReservaService(ReservaRepositoryPort reservaRepositoryPort) {
        this.reservaRepositoryPort = reservaRepositoryPort;
    }

    @Override
    @Transactional
    public Reserva crearReserva(Long clienteId, Long profesionalId, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String notas) {
        if(!fechaHoraInicio.isBefore(fechaHoraFin)){
            throw new IllegalArgumentException("La hora debe ser anterior a la hora final");
        }
        if(fechaHoraInicio.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("No se puede reservar en el pasado");
        }

        reservaRepositoryPort.bloquearAgenda(profesionalId);

        if(reservaRepositoryPort.existeSolapamiento(profesionalId, fechaHoraInicio, fechaHoraFin)){
            throw new IllegalArgumentException("Horario no disponible para este profesional");
        }

        Reserva reserva = new Reserva(clienteId, profesionalId, fechaHoraInicio, fechaHoraFin, notas, null );
        return reservaRepositoryPort.guardar(reserva);
    }

    @Override
    @Transactional
    public Reserva confirmarReserva(Long reservaId, Long usuarioId) {
        Reserva reserva = obtenerReserva(reservaId, usuarioId);
        if(reserva.getEstado() != EstadoReserva.PENDIENTE){
            throw new IllegalStateException("Solo se puede confirmar una reserva Pendiente");
        }
        return cambiarEstado(reserva, EstadoReserva.CONFIRMADA);
    }

    @Override
    @Transactional
    public Reserva cancelarReserva(Long reservaId, Long usuarioId) {
        Reserva reserva = obtenerReserva(reservaId, usuarioId);
        if (reserva.getEstado() != EstadoReserva.PENDIENTE && reserva.getEstado() != EstadoReserva.CONFIRMADA) {
            throw new IllegalStateException("Solo se puede cancelar una reserva Pendiente o Confirmada");
        }

        return cambiarEstado(reserva, EstadoReserva.CANCELADA);
    }

    @Override
    @Transactional
    public Reserva completarReserva(Long reservaId, Long usuarioId) {
        Reserva reserva = obtenerReserva(reservaId, usuarioId);
        if(reserva.getEstado() != EstadoReserva.CONFIRMADA){
            throw new IllegalStateException("Solo se puede completar una reserva Confirmada");
        }

        return cambiarEstado(reserva, EstadoReserva.COMPLETADA);
    }


    private Reserva cambiarEstado(Reserva reserva, EstadoReserva nuevoEstado) {
        reserva.setEstado(nuevoEstado);
        reserva.setFechaActualizacion(LocalDateTime.now());
        return reservaRepositoryPort.guardar(reserva);
    }

    @Override
    public Reserva obtenerReserva(Long reservaId, Long usuarioId) {
        Reserva reserva = reservaRepositoryPort.buscarReservaPorId(reservaId)
                .orElseThrow(() -> new ReservaNoEncontradaException(reservaId));
        validarParticipante(reserva, usuarioId);
        return reserva;
    }

    @Override
    public List<Reserva> listarReservaPorCliente(Long clienteId) {
        return reservaRepositoryPort.listarReservaPorClienteId(clienteId);
    }

    @Override
    public List<Reserva> listarReservaPorProfesional(Long profesionalId) {
        return reservaRepositoryPort.listarReservasPorProfesionalId(profesionalId);
    }

    private void validarParticipante(Reserva reserva, Long usuarioId) {
        boolean esCliente = reserva.getClienteId().equals(usuarioId);
        boolean esProfesional = reserva.getProfesionalId().equals(usuarioId);
        if(!esCliente && !esProfesional){
            throw new AccesoNoAutorizadoException();
        }
    }


}
