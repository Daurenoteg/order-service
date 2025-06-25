package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.repositry.OrderRepository;
import com.example.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository OrderRepository;
    @PostMapping
    @Operation(
            summary = "Создать заказ",
            description = "Создает заказ"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список клубов успешно получен",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка сервера"
            )
    })
    public String placeOrder(@RequestBody Order order) {
        orderService.placeOrder(order);
        return "✅ Order saved and sent to RabbitMQ";
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return OrderRepository.findAll();
    }

}

