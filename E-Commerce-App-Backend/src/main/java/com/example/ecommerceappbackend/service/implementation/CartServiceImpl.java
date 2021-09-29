package com.example.ecommerceappbackend.service.implementation;

import com.example.ecommerceappbackend.exception.ResourceNotFoundException;
import com.example.ecommerceappbackend.model.Cart;
import com.example.ecommerceappbackend.model.Product;
import com.example.ecommerceappbackend.model.User;
import com.example.ecommerceappbackend.repository.CartRepository;
import com.example.ecommerceappbackend.repository.ProductRepository;
import com.example.ecommerceappbackend.repository.UserRepository;
import com.example.ecommerceappbackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addCart(Cart newCart) { cartRepository.save(newCart); }

    @Override
    public Cart getCartByUserId(Long userId){
        return cartRepository.findCartByUserId(userId);
    }

    @Override
    public User getUserById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found with id: " + id));
    }

    @Override
    public List<Product> getProductsInCart(Long cartId) {
        return cartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public void removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId).get();
        List<Product> cartProducts = getProductsInCart(cartId);
        int productIndexToRemove = cartProducts.indexOf(productRepository.findById(productId).get());
        cartProducts.remove(productIndexToRemove);
        cart.setProducts(cartProducts);
        cartRepository.save(cart);
    }


}
