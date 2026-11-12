package com.reservas.pagos.infrastructure.adapter.out.messaging.event;

public record PagoRechazadoEvent(Long reservaId, String motivo){
}
