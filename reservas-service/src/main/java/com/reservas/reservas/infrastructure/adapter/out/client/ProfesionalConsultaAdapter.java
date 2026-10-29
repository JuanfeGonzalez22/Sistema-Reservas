package com.reservas.reservas.infrastructure.adapter.out.client;

import com.reservas.reservas.domain.exception.ServicioUsuariosNoDisponibleException;
import com.reservas.reservas.domain.model.ProfesionalInfo;
import com.reservas.reservas.domain.port.out.ProfesionalConsultaPort;
import feign.FeignException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProfesionalConsultaAdapter implements ProfesionalConsultaPort {

    private final ProfesionalFeignClient profesionalFeignClient;

    public ProfesionalConsultaAdapter(ProfesionalFeignClient profesionalFeignClient) {
        this.profesionalFeignClient = profesionalFeignClient;
    }


    @Override
    public Optional<ProfesionalInfo> consultaPorId(Long profesionalId) {
        try {
            ProfesionalClientResponse response = profesionalFeignClient.obtenerPorUsuarioId(profesionalId);
            return Optional.of(new ProfesionalInfo(response.usuarioId(), response.tarifa()));
        } catch (FeignException.NotFound e) {
            return Optional.empty();
        } catch (FeignException e) {
            throw new ServicioUsuariosNoDisponibleException(e);
        }
    }
}
