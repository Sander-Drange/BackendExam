package com.example.BackendExam.service;

import com.example.BackendExam.model.Machine;
import com.example.BackendExam.model.Subassembly;
import com.example.BackendExam.repository.MachineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
    @Autowired
    private MachineRepository machineRepository;

    public List<Machine> findAll() {
        return machineRepository.findAll();
    }

    public Optional<Machine> findById(Long id) {
        return machineRepository.findById(id);
    }

    public Machine save(Machine machine) {
        return machineRepository.save(machine);
    }

    public void deleteById(Long id) {
        machineRepository.deleteById(id);
    }

    @Transactional
    public Machine update(Long id, Machine machineDetails) {
        Machine machine = machineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Machine not found with id " + id));

        machine.setName(machineDetails.getName());

        if (machineDetails.getSubassemblies() != null) {
            // Replaceing the existing subassemblies with the new ones
            machine.getSubassemblies().clear();
            machine.getSubassemblies().addAll(machineDetails.getSubassemblies());

            // Setting the machine for each new subassembly
            for (Subassembly subassembly : machineDetails.getSubassemblies()) {
                subassembly.setMachine(machine);
            }
        }

        return machineRepository.save(machine);
    }
}
