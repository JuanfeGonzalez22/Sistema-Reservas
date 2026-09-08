package com.reservas.auth.domain.port.out;

import com.reservas.auth.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepositoryPort {

    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorEmail(String email);
    boolean existePorEmail(String email);

}
