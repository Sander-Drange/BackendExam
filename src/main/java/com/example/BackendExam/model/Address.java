package com.example.BackendExam.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Address {

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

    private String street;
    private int streetNumber;
    private String city;
    private String country;

    @ManyToMany(mappedBy = "addresses")
    private Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "deliveryAddress")
    private List<Order> orders;

    public Address(String street,
                   int streetNumber,
                   String city,
                   String country) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.country = country;
    }
}

