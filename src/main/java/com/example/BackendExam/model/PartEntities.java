package com.example.BackendExam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class PartEntities {
    @Id
    @SequenceGenerator(
            name = "part_entity_sequence",
            sequenceName = "part_entity_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "part_entity_sequence"
    )
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "subassembly_id")
    @JsonIgnore
    private Subassembly subassembly;

    public PartEntities(String name, Subassembly subassembly) {
        this.name = name;
        this.subassembly = subassembly;
    }

    public void setSubassembly(Subassembly subassembly) {
        this.subassembly = subassembly;
    }
}
