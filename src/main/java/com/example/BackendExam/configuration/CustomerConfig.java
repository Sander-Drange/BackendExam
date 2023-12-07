package com.example.BackendExam.configuration;

import com.example.BackendExam.model.Address;
import com.example.BackendExam.model.Customer;
import com.example.BackendExam.repository.AddressRepository;
import com.example.BackendExam.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static java.time.Month.*;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
                                        AddressRepository addressRepository) {
        return args -> {

            Address address1 = new Address("Oslostreet", 420, "Oslo", "Norway");
            Address address2 = new Address("MalmoStreet", 2, "Malmo", "Sweeden");

            Customer jason = new Customer(
                    "Jason",
                    "jason@Symbolics.com",
                    LocalDate.of(1895, DECEMBER, 3)
            );
            jason.setAddresses(Set.of(address1));

            Customer mohammed = new Customer(
                    "Mohammed",
                    "mohammed@gmail.com",
                    LocalDate.of(1995, APRIL, 27)
            );
            mohammed.setAddresses(Set.of(address2));

            addressRepository.saveAll(
                    List.of(address1,address2)
            );

            customerRepository.saveAll(
                    List.of(jason, mohammed)
            );
        };
    }

}
