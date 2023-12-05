package com.example.BackendExam.service;

import com.example.BackendExam.model.Machine;
import com.example.BackendExam.repository.MachineRepository;
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
}
