package com.example.BackendExam.service;

import com.example.BackendExam.model.Customer;
import com.example.BackendExam.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Transactional
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));

        customer.setName(customerDetails.getName());

        if (customerDetails.getAddresses() != null) {
            customer.getAddresses().clear();
            customer.getAddresses().addAll(customerDetails.getAddresses());
            customerDetails.getAddresses().forEach(address -> address.setCustomer(customer));
        }

        return customerRepository.save(customer);
    }
}
