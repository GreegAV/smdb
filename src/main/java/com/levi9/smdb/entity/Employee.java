package com.levi9.smdb.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "department_id")
    @NotEmpty(message = "Employee should work somewhere.")
    private Long departmentId;

    @Column(name = "login")
    @NotEmpty(message = "Employee should have login.")
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "Employee should have password.")
    private String password;

    @Column(name = "is_admin")
    @NotEmpty(message = "Is employee an admin.")
    private Boolean isAdmin;

    @Column(name = "email")
    @Email
    @NotEmpty(message = "Employee should have email.")
    private String email;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "assigned_to")
    private Set<Software> software;

}
