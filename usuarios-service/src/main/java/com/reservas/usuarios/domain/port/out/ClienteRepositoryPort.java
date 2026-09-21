package com.reservas.usuarios.domain.port.out;

import com.reservas.usuarios.domain.model.Cliente;

import java.util.Optional;

public interface ClienteRepositoryPort {

    Cliente guardar(Cliente cliente);
    Optional<Cliente> obtenerPorId(Long usuarioId);
    boolean existePorId(Long usuarioId);
}
