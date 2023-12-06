package com.example.BackendExam.controller;

import com.example.BackendExam.model.PartEntities;
import com.example.BackendExam.service.PartEntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<PartEntities> getPartEntities() {
        return partEntitiesService.getPartEntities();
    }

    @PostMapping(path = "{partEntityId}")
    public void registerNewPartEntity(@RequestBody PartEntities partEntities) {
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
