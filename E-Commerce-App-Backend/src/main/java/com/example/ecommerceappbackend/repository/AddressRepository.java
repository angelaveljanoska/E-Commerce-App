package com.example.ecommerceappbackend.repository;

import com.example.ecommerceappbackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByUserId(Long userId);
}
