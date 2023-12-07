package com.example.BackendExam.configuration;

import com.example.BackendExam.model.Address;
import com.example.BackendExam.model.Customer;
import com.example.BackendExam.model.Order;
import com.example.BackendExam.repository.AddressRepository;
import com.example.BackendExam.repository.CustomerRepository;
import com.example.BackendExam.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            CustomerRepository customerRepository,
            AddressRepository addressReository,
            OrderRepository orderRepository) {

        return args -> {

            Address address1 = new Address ("Oslostreet", 100,"Oslo", "Norway");
            Address address2 = new Address ("Swedenstreet", 200,"Gothenburg", "Sweden");
            addressReository.saveAll(List.of(address1, address2));

            Customer michael = new Customer(
                    "Michael",
                    "michaelhalnik@gmail.com",
                    LocalDate.of(2003, Month.AUGUST, 12)
            );
            michael.setAddresses(Set.of(address1));

            Customer bob = new Customer(
                    "Bob",
                    "bob@gmail.com",
                    LocalDate.of(2003, Month.JUNE, 16)
            );
            bob.setAddresses(Set.of(address2));

            customerRepository.saveAll(
                    List.of(michael, bob)
            );

            Order order1 = new Order();
            order1.setCustomer(michael);
            order1.setDeliveryAddress(address1);

            Order order2 = new Order();
            order2.setCustomer(bob);
            order2.setDeliveryAddress(address2);

            orderRepository.saveAll(List.of(order1, order2));
        };
    }
}
