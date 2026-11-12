package com.reservas.reservas.infrastructure.adapter.out.messaging;

import com.reservas.reservas.domain.port.out.EventoPublisherPort;
import com.reservas.reservas.infrastructure.adapter.out.messaging.event.ReservaCreadaEvent;
import com.reservas.reservas.infrastructure.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EventoPublisherAdapter implements EventoPublisherPort {

    private final RabbitTemplate rabbitTemplate;

    public EventoPublisherAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publicarReservaCreada(Long reservaId, Long clienteId, BigDecimal monto) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.RK_RESERVA_CREADA,
                new ReservaCreadaEvent(reservaId, clienteId, monto)
        );
    }
}
