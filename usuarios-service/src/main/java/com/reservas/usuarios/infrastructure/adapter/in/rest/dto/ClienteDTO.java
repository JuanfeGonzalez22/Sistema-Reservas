package com.reservas.usuarios.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

public class ClienteDTO {

    public record ClientePerfilRequest(@NotBlank String telefono, @NotBlank String direccion){
    }

    public record ClientePerfilResponse(Long usuarioId, @NotBlank String telefono, @NotBlank String direccion){
    }

}
