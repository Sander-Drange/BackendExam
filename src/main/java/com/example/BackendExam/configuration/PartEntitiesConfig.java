package com.example.BackendExam.configuration;

import com.example.BackendExam.model.Customer;
import com.example.BackendExam.model.PartEntities;
import com.example.BackendExam.repository.CustomerRepository;
import com.example.BackendExam.repository.PartEntitiesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class PartEntitiesConfig {
    @Bean
    CommandLineRunner commandLineRunner(PartEntitiesRepository partEntitiesRepository) {
        return args -> {
            PartEntities light = new PartEntities(
                    "light"
            );

            PartEntities headband = new PartEntities(
                    "headband"
            );

            partEntitiesRepository.saveAll(
                    List.of(light, headband)
            );
        };
    }
}
