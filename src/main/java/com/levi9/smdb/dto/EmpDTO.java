package com.levi9.smdb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class EmpDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String department;
    private String email;

}
