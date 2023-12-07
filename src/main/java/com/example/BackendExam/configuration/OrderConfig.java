package com.example.BackendExam.configuration;

import com.example.BackendExam.model.Order;
import com.example.BackendExam.model.Customer;
import com.example.BackendExam.model.Address;
import com.example.BackendExam.repository.OrderRepository;
import com.example.BackendExam.repository.CustomerRepository;
import com.example.BackendExam.repository.AddressRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class OrderConfig {

    @Bean
    CommandLineRunner commandLineRunnerForOrder(OrderRepository orderRepository,
                                                CustomerRepository customerRepository,
                                                AddressRepository addressRepository) {
        return args -> {
            Optional<Customer> customerOptional = customerRepository.findById(1L); // Example customer ID
            if (!customerOptional.isPresent()) {
                throw new IllegalStateException("Customer not found");
            }
            Customer customer = customerOptional.get();

            Optional<Address> addressOptional = addressRepository.findById(1L); // Example address ID
            if (!addressOptional.isPresent()) {
                throw new IllegalStateException("Address not found");
            }
            Address address = addressOptional.get();

            Order order1 = new Order();
            order1.setCustomer(customer);
            order1.setDeliveryAddress(address);

            Order order2 = new Order();
            order2.setCustomer(customer);
            order2.setDeliveryAddress(address);

            orderRepository.saveAll(List.of(order1, order2));
        };
    }
}
