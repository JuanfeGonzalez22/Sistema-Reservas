package com.reservas.auth.application.service;

import com.reservas.auth.domain.model.ResultadoAutenticacion;
import com.reservas.auth.domain.model.Rol;
import com.reservas.auth.domain.model.Usuario;
import com.reservas.auth.domain.port.in.AuthUseCase;
import com.reservas.auth.domain.port.out.PasswordEncoderPort;
import com.reservas.auth.domain.port.out.TokenGeneratorPort;
import com.reservas.auth.domain.port.out.UsuarioRepositoryPort;

public class AuthService implements AuthUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenGeneratorPort tokenGeneratorPort;

    public AuthService(UsuarioRepositoryPort usuarioRepositoryPort, PasswordEncoderPort passwordEncoderPort, TokenGeneratorPort tokenGeneratorPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.tokenGeneratorPort = tokenGeneratorPort;
    }
    @Override
    public ResultadoAutenticacion registrarUsuario(String email, String passwordPlano, String nombre) {
        if (usuarioRepositoryPort.existePorEmail(email)) {
            throw new IllegalArgumentException("Usuario existente");
        }

        String password = passwordEncoderPort.encriptar(passwordPlano);
        Usuario usuario = new Usuario(null, email, password, Rol.CLIENTE, nombre);
        Usuario usuarioGuardado = usuarioRepositoryPort.guardar(usuario);
        String token = tokenGeneratorPort.generarToken(usuarioGuardado);
        return new ResultadoAutenticacion(usuarioGuardado, token);
    }

    @Override
    public ResultadoAutenticacion autenticarUsuario(String email, String passwordPlano) {
       Usuario usuario = usuarioRepositoryPort.buscarPorEmail(email)
               .orElseThrow(() -> new IllegalArgumentException("Credenciales Incorrectas"));
       if(!passwordEncoderPort.validar(passwordPlano,  usuario.getPassword())) {
           throw new IllegalArgumentException("Credenciales Incorrectas");
       }
       String token = tokenGeneratorPort.generarToken(usuario);
       return new ResultadoAutenticacion(usuario, token);
    }
}
