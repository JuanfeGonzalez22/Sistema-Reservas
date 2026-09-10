package com.reservas.auth.infrastructure.adapter.in.rest;

import com.reservas.auth.domain.model.ResultadoAutenticacion;
import com.reservas.auth.domain.model.Usuario;
import com.reservas.auth.domain.port.in.AuthUseCase;
import com.reservas.auth.infrastructure.adapter.in.rest.dto.AuthDtos;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/registro")
    public ResponseEntity<AuthDtos.TokenResponse> registrar(@Valid @RequestBody AuthDtos.RegistroRequest request){
        ResultadoAutenticacion resultado = authUseCase.registrarUsuario(request.email(), request.password(), request.nombre());
        Usuario usuario = resultado.getUsuario();
        return ResponseEntity.ok(new AuthDtos.TokenResponse(resultado.getToken(), usuario.getEmail(), usuario.getId(), usuario.getRol().name()));
    }

    @PostMapping("/admin/regitrar")
    public ResponseEntity<Void> registrarPorRol(@Valid @RequestBody AuthDtos.RegistroRequest request){
        authUseCase.registrarUsuario(request.email(), request.password(), request.nombre());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDtos.TokenResponse> login(@Valid @RequestBody AuthDtos.LoginRequest request){
        ResultadoAutenticacion resultado = authUseCase.autenticarUsuario(request.email(), request.password());
        Usuario usuario = resultado.getUsuario();
        return ResponseEntity.ok(new AuthDtos.TokenResponse(resultado.getToken(), usuario.getEmail(), usuario.getId(), usuario.getRol().name()));
    }


}
