package com.example.ecommerceappbackend.service.implementation;

import com.example.ecommerceappbackend.model.*;
import com.example.ecommerceappbackend.model.enumerations.OStatus;
import com.example.ecommerceappbackend.repository.CartRepository;
import com.example.ecommerceappbackend.repository.OrderRepository;
import com.example.ecommerceappbackend.repository.ProductOrderRepository;
import com.example.ecommerceappbackend.repository.ProductRepository;
import com.example.ecommerceappbackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Override
    public void createOrder(Long cartId, List<Product> products) {
        Order newOrder = new Order();
        Cart cart = cartRepository.findById(cartId).get();
        User user = cart.getUser();
        newOrder.setUser(user);
        orderRepository.save(newOrder);
        for(int i=0; i<products.size(); i++) {
            Product currentProduct = productRepository.findById(products.get(i).getId()).get();
            int quantityToOrder = currentProduct.getQuantity() - products.get(i).getQuantity();
            currentProduct.setQuantity(products.get(i).getQuantity());
            productRepository.save(currentProduct);

            ProductOrder pOrder = new ProductOrder();
            pOrder.setOrder(newOrder);
            pOrder.setProduct(currentProduct);
            pOrder.setQuantity(quantityToOrder);
            productOrderRepository.save(pOrder);
        }
        List<Product> newProductList = new ArrayList<>();
        cart.setProducts(newProductList);
        cartRepository.save(cart);
    }

    @Override
    public List<Order> getOrders()
    {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long orderId)
    {
        return orderRepository.findById(orderId).get();
    }

    @Override
    public void setOrderStatus(Long orderId, boolean status) {
        Order order = orderRepository.findById(orderId).get();
        if(status) {
            order.setStatus(OStatus.APPROVED);
        }
        else {
            order.setStatus(OStatus.DECLINED);
        }
        orderRepository.save(order);
    }
}
