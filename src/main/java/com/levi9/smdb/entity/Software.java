package com.levi9.smdb.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "software")
public class Software {

    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Software should have a name.")
    @Column(name = "soft_name")
    private String softName;

    @NotEmpty(message = "Software should have a serial number.")
    @Column(name = "serial")
    private String serial;

    @Column(name = "assigned_to")
    private Long assignedTo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
