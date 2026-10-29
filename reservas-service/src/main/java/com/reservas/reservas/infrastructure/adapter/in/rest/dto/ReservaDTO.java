package com.reservas.reservas.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservaDTO {

    public record CrearReservaRequest(@NotNull(message = "profesionalId es obligatorio") Long profesionalId,
                                      @NotNull(message = "fechaHoraInicio es obligatoria") LocalDateTime fechaHoraInicio,
                                      @NotNull(message = "fechaHoraFin es obligatoria") LocalDateTime fechaHoraFin,
                                      String notas

    ){}

    public record ReservaResponse(Long id,
                                  Long clienteId,
                                  Long profesionalId,
                                  LocalDateTime fechaHoraInicio,
                                  LocalDateTime fechaHoraFin,
                                  String estado,
                                  String notas,
                                  BigDecimal precio,
                                  LocalDateTime fechaCreacion
    ){}

}
