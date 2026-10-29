package com.reservas.reservas.domain.model;

import java.math.BigDecimal;

public class ProfesionalInfo {

    private final Long usuarioId;
    private final BigDecimal tarifa;

    public ProfesionalInfo(Long usuarioId, BigDecimal tarifa) {
        this.usuarioId = usuarioId;
        this.tarifa = tarifa;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
    public BigDecimal getTarifa() {
        return tarifa;
    }

}
