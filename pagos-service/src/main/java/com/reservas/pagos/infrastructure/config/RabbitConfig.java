package com.reservas.pagos.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "reservas.exchange";

    public static final String RK_RESERVA_CREADA = "reserva.creada";
    public static final String RK_PAGO_APROBADO = "pago.aprobado";
    public static final String RK_PAGO_RECHAZADO = "pago.rechazado";

    public static final String QUEUE_RESERVA_CREADA = "pagos.reserva-creada";

    @Bean
    public TopicExchange reservaExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue queueReservaCreada() {
        return new Queue(RK_RESERVA_CREADA, true);
    }

    @Bean
    public Binding bindingReservaCreada() {
        return BindingBuilder
                .bind(queueReservaCreada())
                .to(reservaExchange())
                .with(RK_RESERVA_CREADA);
    }

    @Bean
    public MessageConverter jasoMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

}
