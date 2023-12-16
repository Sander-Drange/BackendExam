package com.example.BackendExam.configuration;

import com.example.BackendExam.model.*;
import com.example.BackendExam.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

@Configuration
public class SystemConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            CustomerRepository customerRepository,
            AddressRepository addressRepsitory,
            OrderRepository orderRepository,
            MachineRepository machineRepository,
            SubassemblyRepository subassemblyRepository,
            PartEntitiesRepository partEntitiesRepository) {

        return args -> {

            Address address1 = new Address ("Oslostreet", 100,"Oslo", "Norway");
            Address address2 = new Address ("Swedenstreet", 200,"Gothenburg", "Sweden");
            addressRepsitory.saveAll(List.of(address1, address2));

            Customer michael = new Customer(
                    "Jason",
                    "jason@gmail.com",
                    LocalDate.of(2003, Month.AUGUST, 12)
            );
            michael.setAddresses(Set.of(address1));

            Customer bob = new Customer(
                    "Bob",
                    "bob@gmail.com",
                    LocalDate.of(1993, Month.JUNE, 16)
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
