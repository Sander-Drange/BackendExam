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
    CommandLineRunner commandLineRunnerForMachine(MachineRepository machineRepository) {
        return args -> {
            Machine machine1 = new Machine("Excavator");
            Machine machine2 = new Machine("Bulldozer");
            Machine machine3 = new Machine("Crane");

            machineRepository.saveAll(List.of(machine1, machine2, machine3));
        };
    }
}
