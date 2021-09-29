package com.example.ecommerceappbackend.repository;

import com.example.ecommerceappbackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
