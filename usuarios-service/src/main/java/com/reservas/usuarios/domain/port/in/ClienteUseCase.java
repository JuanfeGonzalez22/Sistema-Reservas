package com.reservas.usuarios.domain.port.in;

import com.reservas.usuarios.domain.model.Cliente;

public interface ClienteUseCase {

    Cliente crearPerfil(Long usuarioId,String telefono, String direccion);
    Cliente obtenerPerfil(Long usuarioId);
    Cliente actualizarPerfil(Long usuarioId, String telefono, String direccion);


}
