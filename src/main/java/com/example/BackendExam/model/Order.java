package com.example.BackendExam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    private Long id;
    private int customerId; // skal ha en customer, men customer har mange orders
    private int machineId;  // skal ha flere maskiner

    public Order(int customerId, int machineId) {
        this.customerId = customerId;
        this.machineId = machineId;
    }
}
