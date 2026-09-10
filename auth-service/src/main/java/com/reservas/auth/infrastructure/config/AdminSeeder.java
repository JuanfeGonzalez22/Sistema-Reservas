package com.reservas.auth.infrastructure.config;

import com.reservas.auth.domain.model.Rol;
import com.reservas.auth.domain.port.in.AuthUseCase;
import com.reservas.auth.domain.port.out.UsuarioRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final AuthUseCase  authUseCase;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    public AdminSeeder(UsuarioRepositoryPort usuarioRepositoryPort, AuthUseCase authUseCase) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.authUseCase = authUseCase;
    }

    @Override
    public void run(String... args) throws Exception {
        if(!usuarioRepositoryPort.existePorEmail(adminEmail)){
            authUseCase.registrarUsuarioPorRol(adminEmail, adminPassword, "Admin Principal", Rol.ADMIN.name());
        }
    }
}
