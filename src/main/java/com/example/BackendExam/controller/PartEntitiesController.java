package com.example.BackendExam.controller;

import com.example.BackendExam.model.PartEntities;
import com.example.BackendExam.service.PartEntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/part-entities")
public class PartEntitiesController {

    private final PartEntitiesService partEntitiesService;

    @Autowired
    public PartEntitiesController(PartEntitiesService partEntitiesService) {
        this.partEntitiesService = partEntitiesService;
    }

    @GetMapping
    public ResponseEntity<List<PartEntities>> getPartEntities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<PartEntities> partEntities = partEntitiesService.getPartEntities(page, size);
        return ResponseEntity.ok(partEntities);
    }

    @GetMapping("/{partEntityId}")
    public ResponseEntity<PartEntities> getPartEntityById(@PathVariable Long partEntityId) {
        return partEntitiesService.findById(partEntityId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void createPartEntity(@RequestBody PartEntities partEntities) {
        partEntitiesService.addNewPartEntities(partEntities);
    }

    @DeleteMapping(path = "{partEntityId}")
    public void deletePartEntity(@PathVariable("partEntityId") Long pathEntityId) {
        partEntitiesService.deletePartEntities(pathEntityId);
    }

    @PutMapping(path = "{partEntityId}")
    public void updatePartEntity(
            @PathVariable("partEntityId") Long partEntityId,
            @RequestParam(required = false) String newName) {
        partEntitiesService.updatePartEntities(partEntityId, newName);
    }
}
