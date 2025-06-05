package com.database.project.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerRequestDTO {
    private String name;
    private int age;
    private String email;
    private String cellPhone;
}
