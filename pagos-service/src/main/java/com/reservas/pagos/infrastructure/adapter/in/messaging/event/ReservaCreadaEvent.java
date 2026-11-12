package com.reservas.pagos.infrastructure.adapter.in.messaging.event;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReservaCreadaEvent (Long reservaId, Long clienteId, BigDecimal monto){
}

