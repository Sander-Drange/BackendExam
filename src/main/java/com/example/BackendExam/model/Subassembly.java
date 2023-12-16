package com.example.BackendExam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Subassembly {

    @Id
    @SequenceGenerator(
            name = "subassembly_sequence",
            sequenceName = "subassembly_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subassembly_sequence"
    )
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machine machine;

    @OneToMany(mappedBy = "subassembly", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartEntities> parts = new ArrayList<>();

    public Subassembly(String name, Machine machine) {
        this.name = name;
        this.machine = machine;
    }

    public void addPartEntity(PartEntities partEntity) {
        parts.add(partEntity);
        partEntity.setSubassembly(this);
    }

    public void removePartEntity(PartEntities partEntity) {
        parts.remove(partEntity);
        partEntity.setSubassembly(null);
    }

}
