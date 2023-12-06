package com.example.BackendExam.service;

import com.example.BackendExam.model.Subassembly;
import com.example.BackendExam.repository.SubassemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubassemblyService {
    private final SubassemblyRepository subassemblyRepository;

    @Autowired
    public SubassemblyService(SubassemblyRepository subassemblyRepository) {
        this.subassemblyRepository = subassemblyRepository;
    }

    public List<Subassembly> getSubassemblies() {
        return subassemblyRepository.findAll();
    }

    public Subassembly addNewSubassembly(Subassembly subassembly) {
        subassemblyRepository.save(subassembly);
        return subassembly;
    }

    public Optional<Subassembly> findById(Long id) {
        return subassemblyRepository.findById(id);
    }

    public void deleteSubassembly(Long subassemblyId) {
        boolean exists = subassemblyRepository.existsById(subassemblyId);
        if (!exists) {
            throw new IllegalStateException("subassembly with id " + subassemblyId + " does not exist");
        }
        subassemblyRepository.deleteById(subassemblyId);
    }
}

