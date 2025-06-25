// RabbitMQConfig.java
package com.example.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    @Value("${app.rabbitmq.queue}")
    private String queue;

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(queue);
    }

    @Bean
    public Binding binding(Queue orderQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(orderQueue).to(topicExchange).with(routingKey);
    }
}
