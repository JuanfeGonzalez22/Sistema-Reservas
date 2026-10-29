package com.reservas.usuarios.infrastructure.adapter.in.rest;

import com.reservas.usuarios.domain.model.Profesional;
import com.reservas.usuarios.domain.port.in.ProfesionalUseCase;
import com.reservas.usuarios.infrastructure.adapter.in.rest.dto.ProfesionalDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalController {

    private final ProfesionalUseCase profesionalUseCase;

    public ProfesionalController(ProfesionalUseCase profesionalUseCase) {
        this.profesionalUseCase = profesionalUseCase;
    }

    @PostMapping
    public ResponseEntity<ProfesionalDTO.ProfesionalPerfilResponse> crearPerfil(@Valid @RequestBody ProfesionalDTO.ProfesionalPerfilRequest request, @AuthenticationPrincipal Jwt jwt){
        Long usuarioId = jwt.getClaim("id"); // abre el token valido y extrae el dato
        Profesional profesional = profesionalUseCase.crearPerfil(usuarioId, request.especialidad(), request.horarioTrabajo(), request.tarifa());
        return ResponseEntity.ok(toResponse(profesional));
    }

    @GetMapping("/me")
    public ResponseEntity<ProfesionalDTO.ProfesionalPerfilResponse> ObtenerPerfil(@AuthenticationPrincipal Jwt jwt){
        Long usuarioId = jwt.getClaim("id");
        Profesional profesional = profesionalUseCase.obtenerPerfil(usuarioId);
        return ResponseEntity.ok(toResponse(profesional));
    }

    @GetMapping("/{usuarioId}")
    public ProfesionalDTO.ProfesionalPerfilResponse obtenerPorUsuarioId(@PathVariable Long usuarioId){
        return toResponse(profesionalUseCase.obtenerPerfil(usuarioId));
    }

    @PutMapping("/me")
    public ResponseEntity<ProfesionalDTO.ProfesionalPerfilResponse> actualizarPerfil(@Valid @RequestBody ProfesionalDTO.ProfesionalPerfilRequest request, @AuthenticationPrincipal Jwt jwt){
        Long usuarioId = jwt.getClaim("id");
        Profesional profesional = profesionalUseCase.actualizarPerfil(usuarioId, request.especialidad(),  request.horarioTrabajo(), request.tarifa());
        return ResponseEntity.ok(toResponse(profesional));
    }

    private ProfesionalDTO.ProfesionalPerfilResponse toResponse(Profesional profesional){
        return new ProfesionalDTO.ProfesionalPerfilResponse(profesional.getUsuarioId(), profesional.getEspecialidad(),  profesional.getHorarioTrabajo(), profesional.getTarifa());
    }

}
