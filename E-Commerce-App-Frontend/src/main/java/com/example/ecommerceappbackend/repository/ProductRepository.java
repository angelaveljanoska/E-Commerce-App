package com.example.ecommerceappbackend.repository;

import com.example.ecommerceappbackend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //@Query(value = "SELECT p.name FROM Product AS p WHERE p.name = :name")
    Optional<Product> findByName(String name);
    List<Product> findAllByOrderByIdAsc();
}
