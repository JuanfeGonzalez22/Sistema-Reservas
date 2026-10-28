package com.reservas.reservas.infrastructure.adapter.in.rest;

import com.reservas.reservas.domain.model.Reserva;
import com.reservas.reservas.domain.port.in.ReservaUseCase;
import com.reservas.reservas.infrastructure.adapter.in.rest.dto.ReservaDTO.CrearReservaRequest;
import com.reservas.reservas.infrastructure.adapter.in.rest.dto.ReservaDTO.ReservaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaUseCase reservaUseCase;

    public ReservaController(ReservaUseCase reservaUseCase) {
        this.reservaUseCase = reservaUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaResponse crear(@AuthenticationPrincipal Jwt jwt, @RequestBody CrearReservaRequest request) {

        Long clienteId = ((Number) jwt.getClaims().get("id")).longValue();

    Reserva reserva = reservaUseCase.crearReserva(clienteId,
            request.profesionalId(),
            request.fechaHoraInicio(),
            request.fechaHoraFin(),
            request.notas());

    return toResponse(reserva);
    }

    @GetMapping("/mis-reservas")
    public List<ReservaResponse> misReservas(@AuthenticationPrincipal Jwt jwt){
        Long clienteId = ((Number) jwt.getClaims().get("id")).longValue();
        return reservaUseCase.listarReservaPorCliente(clienteId).stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/mi-agenda")
    public List<ReservaResponse> miAgenda(@AuthenticationPrincipal Jwt jwt){
        Long profesionalId = ((Number) jwt.getClaims().get("id")).longValue();
        return reservaUseCase.listarReservaPorProfesional(profesionalId).stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ReservaResponse obtenerReserva(@PathVariable Long id,  @AuthenticationPrincipal Jwt jwt){
        Long usuarioId = ((Number) jwt.getClaims().get("id")).longValue();
        return toResponse(reservaUseCase.obtenerReserva(id, usuarioId));
    }

    @PatchMapping("/{id}/confirmar")
    public ReservaResponse confirmarReserva(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt){
        Long usuarioId = ((Number) jwt.getClaims().get("id")).longValue();
        return toResponse(reservaUseCase.confirmarReserva(id, usuarioId));
    }

    @PatchMapping("{id}/cancelar")
    public ReservaResponse cancelarReserva(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt){
        Long usuarioId = ((Number) jwt.getClaims().get("id")).longValue();
        return toResponse(reservaUseCase.cancelarReserva(id, usuarioId));
    }

    @PatchMapping("/{id}/completar")
    public ReservaResponse completarReserva(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt){
        Long usuarioId = ((Number) jwt.getClaims().get("id")).longValue();
        return toResponse(reservaUseCase.completarReserva(id, usuarioId));
    }

    private ReservaResponse toResponse(Reserva reserva){
        return new ReservaResponse(
                reserva.getId(),
                reserva.getClienteId(),
                reserva.getProfesionalId(),
                reserva.getFechaHoraInicio(),
                reserva.getFechaHoraFin(),
                reserva.getEstado().name(),
                reserva.getNotas(),
                reserva.getPrecio(),
                reserva.getFechaCreacion()
        );
    }

}
