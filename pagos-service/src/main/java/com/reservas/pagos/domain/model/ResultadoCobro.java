package com.reservas.pagos.domain.model;

public record ResultadoCobro(boolean aprobado, String motivo){

    public static ResultadoCobro exitoso() {
        return new ResultadoCobro(true, null);
    }

    public static ResultadoCobro rechazado(String motivo) {
        return new ResultadoCobro(false, motivo);
    }

}

