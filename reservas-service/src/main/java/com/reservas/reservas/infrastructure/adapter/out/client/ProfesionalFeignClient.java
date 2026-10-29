package com.reservas.reservas.infrastructure.adapter.out.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios-service", url = "${app.usuarios-service.url}")
public interface ProfesionalFeignClient {

    @GetMapping("/api/profesionales/{usuarioId}")
    ProfesionalClientResponse obtenerPorUsuarioId(@PathVariable("usuarioId") Long usuarioId);
}
