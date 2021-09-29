package com.example.ecommerceappbackend.controller;

import com.example.ecommerceappbackend.exception.ResourceNotFoundException;
import com.example.ecommerceappbackend.model.Address;
import com.example.ecommerceappbackend.service.implementation.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
public class AddressController {
    @Autowired
    private AddressServiceImpl addressService;

    @PostMapping(path = "/address/{userId}", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void addAddress(@Valid @RequestBody Address address, @PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        address.setUser(addressService.getUserById(userId));
        addressService.addAddress(address);
    }

    @PutMapping("/address/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Address> updateAddress(@PathVariable(value = "id") Long addressId, @Valid @RequestBody Address addressDetails) throws ResourceNotFoundException {
        return addressService.updateAddress(addressId, addressDetails);
    }

    @GetMapping("/address/{userId}")
    public Address getAddressByUserId(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
        return addressService.getAddressByUserId(userId);
    }


}
