package com.reservas.usuarios.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ProfesionalDTO {

    public record ProfesionalPerfilRequest(@NotBlank String especialidad, @NotBlank String horarioTrabajo, @Positive BigDecimal tarifa) {
    }

    public record ProfesionalPerfilResponse(Long usuarioId, @NotBlank String especialidad, @NotBlank String horarioTrabajo, @Positive BigDecimal tarifa) {
    }

}
