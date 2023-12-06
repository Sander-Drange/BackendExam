package com.example.BackendExam.controller;

import com.example.BackendExam.model.Address;
import com.example.BackendExam.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.findAll();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        return addressService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Address savedAddress = addressService.save(address);
        return ResponseEntity.ok(savedAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address address) {
        return ResponseEntity.ok(addressService.update(id, address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /*@PostMapping("/{addressId}/customer/{customerId}")
    public ResponseEntity<?> addAddressToCustomer(@PathVariable Long addressId, @PathVariable Long customerId) {
        addressService.addAddressToCustomer(addressId, customerId);
        return ResponseEntity.ok().build();
    }*/

}
