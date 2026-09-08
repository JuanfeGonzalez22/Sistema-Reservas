package com.reservas.auth.application.service;

import com.reservas.auth.domain.model.Usuario;
import com.reservas.auth.domain.port.in.AuthUseCase;
import com.reservas.auth.domain.port.out.PasswordEncodePort;
import com.reservas.auth.domain.port.out.UsuarioRepositoryPort;

public class AuthService implements AuthUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordEncodePort passwordEncodePort;

    public AuthService(UsuarioRepositoryPort usuarioRepositoryPort, PasswordEncodePort passwordEncodePort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.passwordEncodePort = passwordEncodePort;
    }
    @Override
    public Usuario registrarUsuario(String email, String passwordPlano, String nombre) {
        if (usuarioRepositoryPort.existePorEmail(email)) {
            throw new IllegalArgumentException("Usuario existente");
        }

        String password = passwordEncodePort.encriptar(passwordPlano);
        Usuario usuario = new Usuario(null, email, password, nombre);
        return  usuarioRepositoryPort.guardar(usuario);
    }

    @Override
    public Usuario autenticarUsuario(String email, String passwordPlano) {
       Usuario usuario = usuarioRepositoryPort.buscarPorEmail(email)
               .orElseThrow(() -> new IllegalArgumentException("Credenciales Incorrectas"));
       if(!passwordEncodePort.validar(passwordPlano,  usuario.getPassword())) {
           throw new IllegalArgumentException("Credenciales Incorrectas");
       }
       return usuario;
    }
}
