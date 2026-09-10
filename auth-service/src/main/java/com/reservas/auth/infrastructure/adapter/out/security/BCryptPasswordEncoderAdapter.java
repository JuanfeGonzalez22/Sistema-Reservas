package com.reservas.auth.infrastructure.adapter.out.security;

import com.reservas.auth.domain.port.out.PasswordEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoderPort {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encriptar(String passwordPlano) {
        return encoder.encode(passwordPlano);
    }

    @Override
    public boolean validar(String passwordPlano, String passwordEncriptado) {
        return encoder.matches(passwordPlano, passwordEncriptado);
    }
}
