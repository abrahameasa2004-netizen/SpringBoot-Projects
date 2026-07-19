package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DashboardResponse;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public AdminService(UserRepository userRepository,
                        ProductRepository productRepository,
                        OrderRepository orderRepository,
                        PaymentRepository paymentRepository) {

        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    public DashboardResponse getDashboard() {

        long totalUsers = userRepository.count();
        long totalProducts = productRepository.count();
        long totalOrders = orderRepository.count();
        long totalPayments = paymentRepository.count();

        double revenue = paymentRepository.findAll()
                .stream()
                .mapToDouble(payment -> payment.getAmount())
                .sum();

        return new DashboardResponse(
                totalUsers,
                totalProducts,
                totalOrders,
                totalPayments,
                revenue
        );
    }
}