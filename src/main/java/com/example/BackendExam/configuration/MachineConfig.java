package com.example.BackendExam.configuration;

import com.example.BackendExam.model.Machine;
import com.example.BackendExam.model.PartEntities;
import com.example.BackendExam.model.Subassembly;
import com.example.BackendExam.repository.MachineRepository;
import com.example.BackendExam.repository.PartEntitiesRepository;
import com.example.BackendExam.repository.SubassemblyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MachineConfig {

    @Bean
    CommandLineRunner machineDataLoader(
            MachineRepository machineRepository,
            SubassemblyRepository subassemblyRepository,
            PartEntitiesRepository partEntitiesRepository) {
        return args -> {
            Machine computer = new Machine("computer");
            Machine sewingMachine = new Machine("sewing machine");
            machineRepository.saveAll(List.of(computer, sewingMachine));

            Subassembly computerMotherboard = new Subassembly("Motherboard", computer);
            Subassembly sewingMachineNeedle = new Subassembly("Needle Assembly", sewingMachine);
            subassemblyRepository.saveAll(List.of(computerMotherboard, sewingMachineNeedle));

            PartEntities cpu = new PartEntities("CPU", computerMotherboard);
            PartEntities ram = new PartEntities("RAM", computerMotherboard);
            PartEntities needle = new PartEntities("Needle", sewingMachineNeedle);
            PartEntities footPedal = new PartEntities("Foot pedal", sewingMachineNeedle);
            partEntitiesRepository.saveAll(List.of(cpu, ram, needle, footPedal));
        };
    }

}