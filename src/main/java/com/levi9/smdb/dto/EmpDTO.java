package com.levi9.smdb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String department;
    private String email;

}
