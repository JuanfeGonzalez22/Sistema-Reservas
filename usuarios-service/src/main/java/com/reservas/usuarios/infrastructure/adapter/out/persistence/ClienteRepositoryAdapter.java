package com.reservas.usuarios.infrastructure.adapter.out.persistence;

import com.reservas.usuarios.domain.model.Cliente;
import com.reservas.usuarios.domain.port.out.ClienteRepositoryPort;

import java.util.Optional;

public class ClienteRepositoryAdapter implements ClienteRepositoryPort {

    private ClienteJpaRepository clienteJpaRepository;

    public ClienteRepositoryAdapter(ClienteJpaRepository clienteJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        ClienteEntity entity = clienteJpaRepository.findByUsuarioId(cliente.getUsuarioId())
                .map(existing -> {
                    existing.setTelefono(cliente.getTelefono());
                    existing.setDireccion(cliente.getDireccion());
                    return existing;
                })
                .orElse(new ClienteEntity(null, cliente.getUsuarioId(), cliente.getTelefono(), cliente.getDireccion()));

        ClienteEntity guardada = clienteJpaRepository.save(entity);
        return toDomain(guardada);
    }

    @Override
    public Optional<Cliente> obtenerPorId(Long usuarioId) {
        return clienteJpaRepository.findByUsuarioId(usuarioId).map(this :: toDomain);
    }

    @Override
    public boolean existePorId(Long usuarioId) {
        return clienteJpaRepository.existsByUsuarioId(usuarioId);
    }

    private Cliente toDomain(ClienteEntity entity) {
        return new Cliente(entity.getUsuarioId(), entity.getTelefono(), entity.getDireccion());
    }
}
