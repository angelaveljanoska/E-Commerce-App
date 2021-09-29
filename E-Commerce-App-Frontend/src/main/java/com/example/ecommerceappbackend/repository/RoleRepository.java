package com.example.ecommerceappbackend.repository;

import com.example.ecommerceappbackend.model.Role;
import com.example.ecommerceappbackend.model.enumerations.URole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName (URole name);
}
