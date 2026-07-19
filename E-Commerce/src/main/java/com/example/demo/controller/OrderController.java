package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/checkout")
    public Order checkout(@RequestParam String email) {
        return service.checkout(email);
    }

    @GetMapping
    public List<Order> getOrders(@RequestParam String email) {
        return service.getOrders(email);
    }
}