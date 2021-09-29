package com.example.ecommerceappbackend.repository;

import com.example.ecommerceappbackend.model.Product;
import com.example.ecommerceappbackend.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    List<ProductOrder> findAllByOrderId(Long orderId);
}
