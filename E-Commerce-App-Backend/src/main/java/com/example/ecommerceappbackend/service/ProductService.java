package com.example.ecommerceappbackend.service;

import com.example.ecommerceappbackend.exception.ResourceNotFoundException;
import com.example.ecommerceappbackend.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductService {
    public List<Product> listProducts();

    public ResponseEntity<Product> listProduct(Long productId)
            throws ResourceNotFoundException;

    public void addProduct(Product newProduct);

    public ResponseEntity<Product> updateProduct(Long productId,
                                                 @Valid @RequestBody Product productDetails) throws ResourceNotFoundException;

    public Map<String, Boolean> deleteProduct(Long productId)
            throws ResourceNotFoundException;

    public ResponseEntity<Product> searchProduct(String name)
            throws ResourceNotFoundException;

    public void addProductToCart(Long productId, Long cartId);
}
