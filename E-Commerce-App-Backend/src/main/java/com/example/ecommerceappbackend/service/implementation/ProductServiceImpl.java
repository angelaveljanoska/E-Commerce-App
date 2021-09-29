package com.example.ecommerceappbackend.service.implementation;

import com.example.ecommerceappbackend.exception.ResourceNotFoundException;
import com.example.ecommerceappbackend.model.Cart;
import com.example.ecommerceappbackend.model.Product;
import com.example.ecommerceappbackend.repository.CartRepository;
import com.example.ecommerceappbackend.repository.ProductRepository;
import com.example.ecommerceappbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
//import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Product> listProducts() {
        return productRepository.findAllByOrderByIdAsc();
    }

    @Override
    public ResponseEntity<Product> listProduct(Long productId)
            throws ResourceNotFoundException {
            Product  product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id:" + productId));
                return ResponseEntity.ok().body(product);
    }

    @Override
    public void addProduct(Product newProduct) {
        productRepository.save(newProduct);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Long productId,
                                                   @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id : " + productId));

        product.setPhoto(productDetails.getPhoto());
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setQuantity(productDetails.getQuantity());
        product.setPrice(productDetails.getPrice());

        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }
    @Override
    public Map<String, Boolean> deleteProduct(Long productId)
        throws ResourceNotFoundException {
      Product product = productRepository.findById(productId)
      .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id: " + productId));

      productRepository.delete(product);
      Map<String, Boolean> response = new HashMap<>();
      response.put("deleted", Boolean.TRUE);
      return response;
    }

    @Override
    public ResponseEntity<Product> searchProduct(String productName)
            throws ResourceNotFoundException {
        Product  product = productRepository.findByName(productName)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this name:" + productName));
        return ResponseEntity.ok().body(product);
    }

    @Override
    public void addProductToCart(Long productId, Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        Product product = productRepository.findById(productId).get();
        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

}
