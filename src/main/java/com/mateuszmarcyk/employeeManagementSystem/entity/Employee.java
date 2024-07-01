package com.mateuszmarcyk.employeeManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "is required")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "is required")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "is required")
    @Email(message = "Incorrect email format")
    @Column(name = "email")
    private String email;

}
