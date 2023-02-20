package com.levi9.smdb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoftDTO {

    Long id;
    String softName;
    String serial;
    Long assignedTo;

}
