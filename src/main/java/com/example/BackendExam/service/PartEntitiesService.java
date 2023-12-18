package com.example.BackendExam.service;

import com.example.BackendExam.model.PartEntities;
import com.example.BackendExam.repository.PartEntitiesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartEntitiesService {
    private final PartEntitiesRepository partEntitiesRepository;

    @Autowired
    public PartEntitiesService(PartEntitiesRepository partEntitiesRepository) {
        this.partEntitiesRepository = partEntitiesRepository;
    }

    public List<PartEntities> getPartEntities() {
        return partEntitiesRepository.findAll();
    }

    public Optional<PartEntities> findById(Long id) {
        return partEntitiesRepository.findById(id);
    }

    public void addNewPartEntities(PartEntities partEntities) {
        partEntitiesRepository.save(partEntities);
    }

    public void deletePartEntities(Long partEntitiesId) {
        boolean exists = partEntitiesRepository.existsById(partEntitiesId);
        if (!exists) {
            throw new IllegalStateException("part entity with id " + partEntitiesId + " does not exist");
        }
        partEntitiesRepository.deleteById(partEntitiesId);
    }

    @Transactional
    public void updatePartEntities(Long partEntitiesId, String newName) {
        Optional<PartEntities> optionalPartEntities = partEntitiesRepository.findById(partEntitiesId);
        if (optionalPartEntities.isEmpty()) {
            throw new IllegalStateException("Part entity with id " + partEntitiesId + " does not exist");
        }

        PartEntities partEntities = optionalPartEntities.get();
        partEntities.setName(newName);

        partEntitiesRepository.save(partEntities);
    }

}
