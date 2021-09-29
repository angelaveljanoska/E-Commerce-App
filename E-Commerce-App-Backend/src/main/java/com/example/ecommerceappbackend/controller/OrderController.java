package com.example.ecommerceappbackend.controller;

import com.example.ecommerceappbackend.model.Order;
import com.example.ecommerceappbackend.model.Product;
import com.example.ecommerceappbackend.model.ProductOrder;
import com.example.ecommerceappbackend.repository.ProductOrderRepository;
import com.example.ecommerceappbackend.service.OrderService;
import com.example.ecommerceappbackend.service.implementation.CartServiceImpl;
import com.example.ecommerceappbackend.service.implementation.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @PostMapping(path = "/submit/{cartId}", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void addOrder(@PathVariable(value="cartId") Long cartId, @Valid @RequestBody List<Product> products) {
        orderService.createOrder(cartId, products);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/productorders/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductOrder> getProductOrdersByOrderId(@PathVariable(value = "orderId") Long orderId) {
        return productOrderRepository.findAllByOrderId(orderId);
    }

    @GetMapping("/productorders")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductOrder> getProductOrders() {
        return productOrderRepository.findAll();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void setOrderStatus(@PathVariable (value = "id") Long orderId, @RequestBody @Valid boolean status) {
        orderService.setOrderStatus(orderId, status);
    }
}
