package com.example.BackendExam.configuration;

import com.example.BackendExam.model.Customer;
import com.example.BackendExam.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            Customer jason = new Customer(
                    "Jason",
                    "jason@Symbolics.com",
                    LocalDate.of(1895, DECEMBER, 3),
                    21
            );

            Customer mohammed = new Customer(
                    "Mohammed",
                    "mohammed@gmail.com",
                    LocalDate.of(1995, APRIL, 27),
                    21
            );

            customerRepository.saveAll(
                    List.of(jason, mohammed)
            );
        };
    }

}
