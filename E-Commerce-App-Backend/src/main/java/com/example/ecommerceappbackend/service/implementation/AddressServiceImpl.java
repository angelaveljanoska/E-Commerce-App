package com.example.ecommerceappbackend.service.implementation;

import com.example.ecommerceappbackend.exception.ResourceNotFoundException;
import com.example.ecommerceappbackend.model.Address;
import com.example.ecommerceappbackend.model.User;
import com.example.ecommerceappbackend.repository.AddressRepository;
import com.example.ecommerceappbackend.repository.UserRepository;
import com.example.ecommerceappbackend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addAddress(Address newAddress) {
        addressRepository.save(newAddress);
    }

    @Override
    public ResponseEntity<Address> updateAddress(Long addressId, @Valid @RequestBody Address addressDetails) throws ResourceNotFoundException {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id:" + addressId));
        address.setAddressName(addressDetails.getAddressName());
        address.setCity(addressDetails.getCity());
        address.setCountry(addressDetails.getCountry());
        address.setState(addressDetails.getState());
        address.setZipcode(addressDetails.getZipcode());
        address.setPhone(addressDetails.getPhone());

        final Address updatedAddress = addressRepository.save(address);
        return ResponseEntity.ok(updatedAddress);
    }
   @Override
   public Address getAddressByUserId(Long userId) {
        return addressRepository.findAddressByUserId(userId);
   }

   @Override
   public User getUserById(Long id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found with id: " + id));
   }
}
