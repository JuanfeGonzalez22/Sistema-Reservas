package com.reservas.auth.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

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
}
