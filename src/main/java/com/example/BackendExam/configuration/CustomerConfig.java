package com.example.BackendExam.configuration;

import com.example.BackendExam.model.Customer;
import com.example.BackendExam.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            CustomerRepository customerRepository) {
        return args -> {
            Customer michael = new Customer(
                    "Michael",
                    "michaelhalnik@gmail.com",
                    LocalDate.of(2003, Month.AUGUST, 12)
            );

            Customer bob = new Customer(
                    "Bob",
                    "bob@gmail.com",
                    LocalDate.of(2003, Month.JUNE, 16)
            );

            customerRepository.saveAll(
                    List.of(michael, bob)
            );
        };
    }
}
