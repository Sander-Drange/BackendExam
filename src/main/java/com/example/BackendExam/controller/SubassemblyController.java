package com.example.BackendExam.controller;

import com.example.BackendExam.model.Subassembly;
import com.example.BackendExam.service.SubassemblyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subassemblies")
public class SubassemblyController {

    @Autowired
    private SubassemblyService subassemblyService;

    @GetMapping
    public ResponseEntity<List<Subassembly>> getAllSubassemblies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Subassembly> subassemblies = subassemblyService.getSubassemblies(page, size);
        return ResponseEntity.ok(subassemblies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subassembly> getSubassemblyById(@PathVariable Long id) {
        return subassemblyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Subassembly> createSubassembly(@RequestBody Subassembly subassembly) {
        Subassembly newSubassembly = subassemblyService.addNewSubassembly(subassembly);
        return ResponseEntity.ok(newSubassembly);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubassembly(@PathVariable Long id) {
        subassemblyService.deleteSubassembly(id);
        return ResponseEntity.ok().build();
    }

}
