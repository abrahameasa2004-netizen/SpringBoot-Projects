package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Cart;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/add/{productId}")
    public Cart addToCart(@RequestParam String email,
                          @PathVariable Long productId) {

        return service.addToCart(email, productId);
    }

    @GetMapping
    public Cart getCart(@RequestParam String email) {

        return service.getCart(email);
    }

    @DeleteMapping("/item/{cartItemId}")
    public String removeItem(@PathVariable Long cartItemId) {

        return service.removeItem(cartItemId);
    }

}