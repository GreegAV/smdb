package com.levi9.smdb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "departments")
public class Department {

    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dep_code")
    @NotEmpty(message = "Department should be with code.")
    private String depCode;

    @Column(name = "dep_name")
    @NotEmpty(message = "Department should be named.")
    private String depName;

}
