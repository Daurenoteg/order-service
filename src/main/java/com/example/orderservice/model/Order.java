package com.example.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders_rabit")
@Builder
public class Order implements Serializable {
    @Id
    private String orderId;
    private String product;
    private int quantity;
    private double price;
}
