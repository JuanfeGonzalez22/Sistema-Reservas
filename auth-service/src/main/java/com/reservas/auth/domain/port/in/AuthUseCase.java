package com.reservas.auth.domain.port.in;

import com.reservas.auth.domain.model.Usuario;

public interface AuthUseCase {

    Usuario registrarUsuario(String email, String passwordPlano, String nombre);

    Usuario autenticarUsuario(String email, String passwordPlano);
}
