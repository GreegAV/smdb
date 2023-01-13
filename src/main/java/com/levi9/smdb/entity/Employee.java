package com.levi9.smdb.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "employees")
public class Employee {

    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "Employee should have first name.")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Employee should have last name.")
    private String lastName;
    @Column(name = "department")
    @NotEmpty(message = "Employee should work somewhere.")
    private Long department;

    @Column(name = "email")
    @Email
    @NotEmpty(message = "Employee should have email.")
    private String email;

}
