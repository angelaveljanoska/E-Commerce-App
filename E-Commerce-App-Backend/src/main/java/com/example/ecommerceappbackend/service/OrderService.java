package com.example.ecommerceappbackend.service;


import com.example.ecommerceappbackend.model.Order;
import com.example.ecommerceappbackend.model.Product;
import com.example.ecommerceappbackend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OrderService {
    public void createOrder(Long cartId, List<Product> products);
    public List<Order> getOrders();
    public Order getOrderById(Long orderId);
    public void setOrderStatus(Long orderId, boolean status);
}
