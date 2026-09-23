package com.reservas.usuarios.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

public class ProfesionalDTO {

    public record ProfesionalPerfilRequest(@NotBlank String especialidad, @NotBlank String horarioTrabajo){
    }

    public record ProfesionalPerfilResponse(Long usuarioId, @NotBlank String especialidad, @NotBlank String horarioTrabajo){
    }

}
