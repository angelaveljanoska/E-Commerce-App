package com.example.ecommerceappbackend.controller;

import com.example.ecommerceappbackend.exception.ResourceNotFoundException;
import com.example.ecommerceappbackend.model.Address;
import com.example.ecommerceappbackend.model.Cart;
import com.example.ecommerceappbackend.model.Product;
import com.example.ecommerceappbackend.service.implementation.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @PostMapping(path = "/cart/{userId}", consumes = "application/json", produces = "application/json")
    public void addCart(@Valid @RequestBody Cart cart, @PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        cart.setUser(cartService.getUserById(userId));
        cartService.addCart(cart);
    }

    @GetMapping("/cart/{userId}")
    public Cart getCartByUserId(@PathVariable(value = "userId") Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @GetMapping("/cart/{cartId}/products")
    public List<Product> getProductsInCart(@PathVariable(value = "cartId") Long cartId) {
        return cartService.getProductsInCart(cartId);
    }

    @DeleteMapping("/cart/{cartId}/{productId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void removeProductFromCart(@PathVariable(value = "cartId") Long cartId, @PathVariable(value = "productId") Long productId) {
        cartService.removeProductFromCart(cartId, productId);
    }

}
