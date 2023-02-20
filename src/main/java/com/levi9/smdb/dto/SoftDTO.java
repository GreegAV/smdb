package com.levi9.smdb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SoftDTO {

    Long id;
    String softName;
    String serial;
    Long assignedTo;

}
