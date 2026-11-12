package com.reservas.reservas.infrastructure.adapter.out.messaging.event;

import java.math.BigDecimal;

public record ReservaCreadaEvent(Long reservaId, Long clienteId, BigDecimal monto) {
}
