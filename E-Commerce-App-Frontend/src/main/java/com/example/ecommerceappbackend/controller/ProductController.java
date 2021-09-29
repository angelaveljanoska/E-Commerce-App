package com.example.ecommerceappbackend.controller;

import com.example.ecommerceappbackend.exception.ResourceNotFoundException;
import com.example.ecommerceappbackend.model.Product;
import com.example.ecommerceappbackend.service.implementation.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins="*")
@RestController
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    //get all products
    @GetMapping("/products")
    public List<Product> getProducts() {
       return productService.listProducts();
    }
    //get product by id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException {
        return productService.listProduct(productId);
    }

    //add new product
    @PostMapping(path="/products", consumes = "application/json", produces = "application/json")
    //@PreAuthorize("hasRole('ADMIN')")
    public void addProduct(@Valid @RequestBody Product product)
    {
        productService.addProduct(product);
    }

    //update product
    @PutMapping("/products/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable(value="id") Long productId, @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
        return productService.updateProduct(productId, productDetails);
    }

    //delete product
    @DeleteMapping("/products/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public Map<String, Boolean> deleteProduct(@PathVariable(value="id") Long productId)
        throws ResourceNotFoundException {
        return productService.deleteProduct(productId);
    }

}
