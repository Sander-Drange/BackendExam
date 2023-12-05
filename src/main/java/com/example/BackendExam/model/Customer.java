package com.example.BackendExam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Customer {
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private Integer age;

    public Customer (){
    }


}
