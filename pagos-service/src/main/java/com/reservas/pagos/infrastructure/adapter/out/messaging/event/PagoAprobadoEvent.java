package com.reservas.pagos.infrastructure.adapter.out.messaging.event;

public record PagoAprobadoEvent(Long reservaId, Long pagoId){
}
