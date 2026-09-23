package com.reservas.usuarios.infrastructure.adapter.in.rest;

import com.reservas.usuarios.domain.model.Cliente;
import com.reservas.usuarios.domain.port.in.ClienteUseCase;
import com.reservas.usuarios.infrastructure.adapter.in.rest.dto.ClienteDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteUseCase clienteUseCase;

    public ClienteController(ClienteUseCase clienteUseCase) {
        this.clienteUseCase = clienteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO.ClientePerfilResponse> crearPerfil(@Valid @RequestBody ClienteDTO.ClientePerfilRequest request, @AuthenticationPrincipal Jwt jwt){
        Long usuarioId = jwt.getClaim("id");
        Cliente cliente = clienteUseCase.crearPerfil(usuarioId, request.telefono(),  request.direccion());
        return ResponseEntity.ok(toResponse(cliente));
    }

    @GetMapping("/me")
    public ResponseEntity<ClienteDTO.ClientePerfilResponse> obtenerPerfil(@AuthenticationPrincipal Jwt jwt){
        Long usuarioId = jwt.getClaim("id");
        Cliente cliente = clienteUseCase.obtenerPerfil(usuarioId);
        return ResponseEntity.ok(toResponse(cliente));
    }

    @GetMapping("/me")
    public ResponseEntity<ClienteDTO.ClientePerfilResponse> actualizarPerfil(@Valid @RequestBody ClienteDTO.ClientePerfilRequest request, @AuthenticationPrincipal Jwt jwt){
        Long usuarioId = jwt.getClaim("id");
        Cliente cliente = clienteUseCase.actualizarPerfil(usuarioId, request.telefono(), request.direccion());
        return ResponseEntity.ok(toResponse(cliente));
    }

    private ClienteDTO.ClientePerfilResponse toResponse(Cliente cliente){
        return new ClienteDTO.ClientePerfilResponse(cliente.getUsuarioId(), cliente.getTelefono(), cliente.getDireccion());
    }
}
