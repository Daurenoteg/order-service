package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.example.orderservice.producer.OrderProducer;
import com.example.orderservice.repositry.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public Order placeOrder(Order order) {
        order.setOrderId(UUID.randomUUID().toString());

        Order entity = Order.builder()
                .orderId(order.getOrderId())
                .product(order.getProduct())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .build();

        orderRepository.save(entity);
        orderProducer.sendOrder(order); // отправляем в очередь

        return order;
    }
}
