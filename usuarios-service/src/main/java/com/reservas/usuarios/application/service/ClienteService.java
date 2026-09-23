package com.reservas.usuarios.application.service;

import com.reservas.usuarios.domain.model.Cliente;
import com.reservas.usuarios.domain.port.in.ClienteUseCase;
import com.reservas.usuarios.domain.port.out.ClienteRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements ClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public Cliente crearPerfil(Long usuarioId, String telefono, String direccion) {
        if(clienteRepositoryPort.existePorId(usuarioId)) {
            throw new RuntimeException("El usuario ya existe");
        }
        Cliente cliente = new Cliente(usuarioId, telefono, direccion);
        return clienteRepositoryPort.guardar(cliente);
    }

    @Override
    public Cliente obtenerPerfil(Long usuarioId) {
        return clienteRepositoryPort.obtenerPorId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public Cliente actualizarPerfil(Long usuarioId, String telefono, String direccion) {
        Cliente cliente = obtenerPerfil(usuarioId);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccion);
        return clienteRepositoryPort.guardar(cliente);
    }
}
