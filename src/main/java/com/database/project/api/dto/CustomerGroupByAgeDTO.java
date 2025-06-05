package com.database.project.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerGroupByAgeDTO {
    private int age;
    private List<CustomerResponseDTO> list;
}
