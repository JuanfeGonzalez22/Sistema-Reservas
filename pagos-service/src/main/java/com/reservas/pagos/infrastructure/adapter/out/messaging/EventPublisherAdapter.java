package com.reservas.pagos.infrastructure.adapter.out.messaging;

import com.reservas.pagos.domain.port.out.EventoPublisherPort;
import com.reservas.pagos.infrastructure.adapter.out.messaging.event.PagoAprobadoEvent;
import com.reservas.pagos.infrastructure.adapter.out.messaging.event.PagoRechazadoEvent;
import com.reservas.pagos.infrastructure.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class EventPublisherAdapter implements EventoPublisherPort {

    private final RabbitTemplate rabbitTemplate;

    public EventPublisherAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void publicarPagoAprobado(Long reservaId, Long pagoId) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.RK_PAGO_APROBADO,
                new PagoAprobadoEvent(reservaId, pagoId)
        );
    }

    @Override
    public void publicarPagoRechazado(Long reservaId, String motivo) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.RK_PAGO_RECHAZADO,
                new PagoRechazadoEvent(reservaId, motivo)
        );
    }
}
