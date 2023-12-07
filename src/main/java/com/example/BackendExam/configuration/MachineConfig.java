package com.example.BackendExam.configuration;

import com.example.BackendExam.model.Machine;
import com.example.BackendExam.repository.MachineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MachineConfig {
    @Bean
    CommandLineRunner machineDataLoader(
            MachineRepository machineRepository) {
        return args -> {
            Machine computer = new Machine(
                    "computer"
            );

            Machine sewingMachine = new Machine(
                    "sewing machine"
            );

            machineRepository.saveAll(
                    List.of(computer, sewingMachine)
            );
        };
    }
}
