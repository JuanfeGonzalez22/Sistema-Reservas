package com.reservas.auth.infrastructure.adapter.in.rest.dto;

import com.reservas.auth.domain.model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AuthDtos {

    public record RegistroRequest(@NotBlank @Email String email,
                                  @NotBlank String password,
                                  @NotBlank String nombre
    ) {
    }

    public record LoginRequest(@NotBlank @Email String email,
                               @NotBlank String password
    ) {
    }

    public record TokenResponse(String token, String email, Long id, String rol
    ) {
    }

    public record RegistroConRolResponse(@NotBlank @Email String email,
                                         @NotBlank String password,
                                         @NotBlank String nombre,
                                         @NotNull Rol rol
    ){
    }
}
