package com.example.ecommerceappbackend.repository;

import com.example.ecommerceappbackend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
