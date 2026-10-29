package com.reservas.reservas.domain.port.out;

import com.reservas.reservas.domain.model.ProfesionalInfo;

import java.util.Optional;

public interface ProfesionalConsultaPort {

    Optional<ProfesionalInfo> consultaPorId(Long profesionalId);

}
