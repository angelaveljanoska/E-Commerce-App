package com.example.ecommerceappbackend.service;

import com.example.ecommerceappbackend.exception.ResourceNotFoundException;
import com.example.ecommerceappbackend.model.Address;
import com.example.ecommerceappbackend.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

public interface AddressService {

    public void addAddress(Address newAddress);
    public ResponseEntity<Address> updateAddress(Long addressId,
                                                 @Valid @RequestBody Address addressDetails)  throws ResourceNotFoundException;
    public Address getAddressByUserId(Long userId) throws ResourceNotFoundException;
    public User getUserById(Long id) throws ResourceNotFoundException;
}
