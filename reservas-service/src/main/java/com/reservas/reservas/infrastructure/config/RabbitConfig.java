package com.reservas.reservas.infrastructure.config;

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

    public static final String QUEUE_PAGO_APROBADO = "reservas.pago-aprobado";
    public static final String QUEUE_PAGO_RECHAZADO = "reservas.pago-rechazado";

    @Bean
    public TopicExchange reservasExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue queuePagoAprobado() {
        return new Queue(QUEUE_PAGO_APROBADO, true);
    }

    @Bean
    public Queue queuePagoRechazado() {
        return new Queue(QUEUE_PAGO_RECHAZADO, true);
    }

    @Bean
    public Binding bindingPagoAprobado() {
        return BindingBuilder
                .bind(queuePagoAprobado())
                .to(reservasExchange())
                .with(RK_PAGO_APROBADO);
    }

    @Bean
    public Binding bindingPagoRechazado() {
        return BindingBuilder
                .bind(queuePagoRechazado())
                .to(reservasExchange())
                .with(RK_PAGO_RECHAZADO);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

}
