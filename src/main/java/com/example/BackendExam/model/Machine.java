package com.example.BackendExam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Subassembly> subassemblies = new ArrayList<>();

    public Machine(String name) {
        this.name = name;
    }

    public void addSubassembly(Subassembly subassembly) {
        subassemblies.add(subassembly);
        subassembly.setMachine(this);
    }

    public void removeSubassembly(Subassembly subassembly) {
        subassemblies.remove(subassembly);
        subassembly.setMachine(null);
    }
}
