package com.lwl.currency.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private double salary;
}
