package com.example.BackendExam.service;

import com.example.BackendExam.model.Address;
import com.example.BackendExam.model.Customer;
import com.example.BackendExam.repository.AddressRepository;
import com.example.BackendExam.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    @Transactional
    public Address update(Long id, Address addressDetails) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id " + id));

        address.setStreet(addressDetails.getStreet());
        address.setCity(addressDetails.getCity());
        address.setCountry(addressDetails.getCountry());

        // Handling related customers (if necessary)
        // This part depends on your business logic. You might need to add/remove customers,
        // or update the relationship in some other way.

        return addressRepository.save(address);
    }

    /*public void addAddressToCustomer(Long addressId, Long customerId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + addressId));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        customer.getAddresses().add(address);
        address.getCustomers().add(customer);

        customerRepository.save(customer);
        addressRepository.save(address);
    }*/
}
