package com.example.ecommerceappbackend.service;

import com.example.ecommerceappbackend.exception.ResourceNotFoundException;
import com.example.ecommerceappbackend.model.Cart;
import com.example.ecommerceappbackend.model.Product;
import com.example.ecommerceappbackend.model.User;

import java.util.List;

public interface CartService {
    public Cart getCartByUserId(Long userId) throws ResourceNotFoundException;
    public User getUserById(Long id) throws ResourceNotFoundException;
    public void addCart(Cart newCart);
    public List<Product> getProductsInCart(Long id);
    public void removeProductFromCart(Long cartId, Long productId);
}
