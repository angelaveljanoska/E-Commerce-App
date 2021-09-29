package com.example.ecommerceappbackend.repository;

import com.example.ecommerceappbackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
