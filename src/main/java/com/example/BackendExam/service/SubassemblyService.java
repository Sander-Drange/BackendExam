package com.example.BackendExam.service;

import com.example.BackendExam.model.Subassembly;
import com.example.BackendExam.repository.SubassemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void addNewSubassembly(Subassembly subassembly) {
        subassemblyRepository.save(subassembly);
    }

    public void deleteSubassembly(Long subassemblyId) {
        boolean exists = subassemblyRepository.existsById(subassemblyId);
        if (!exists) {
            throw new IllegalStateException("subassembly with id " + subassemblyId + " does not exist");
        }
        subassemblyRepository.deleteById(subassemblyId);
    }
}

