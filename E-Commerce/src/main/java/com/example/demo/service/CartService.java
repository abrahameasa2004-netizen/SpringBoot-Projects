package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       ProductRepository productRepository,
                       UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart addToCart(String email, Long productId) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepository.save(newCart);
        });

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existing =
                cartItemRepository.findByCartAndProduct(cart, product);

        if (existing.isPresent()) {

            CartItem item = existing.get();

            item.setQuantity(item.getQuantity() + 1);
            item.setSubtotal(item.getQuantity() * product.getPrice());

            cartItemRepository.save(item);

        } else {

            CartItem item = new CartItem();

            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(1);
            item.setSubtotal(product.getPrice());

            cartItemRepository.save(item);

            cart.getCartItems().add(item);
        }

        double total = cart.getCartItems()
                .stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();

        cart.setTotalPrice(total);

        return cartRepository.save(cart);
    }

    public Cart getCart(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public String removeItem(Long cartItemId) {

        cartItemRepository.deleteById(cartItemId);

        return "Item Removed Successfully";
    }

}