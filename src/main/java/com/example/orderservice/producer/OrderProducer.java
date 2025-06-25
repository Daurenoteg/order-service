package com.example.orderservice.producer;

import com.example.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    public void sendOrder(Order order) {
        rabbitTemplate.convertAndSend(exchange, routingKey, order);
        System.out.println("Order sent to queue: " + order);
    }
}
