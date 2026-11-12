package com.reservas.reservas.domain.port.out;

import java.math.BigDecimal;

public interface EventoPublisherPort {
    void publicarReservaCreada(Long reservaId, Long clienteId, BigDecimal monto);
}
