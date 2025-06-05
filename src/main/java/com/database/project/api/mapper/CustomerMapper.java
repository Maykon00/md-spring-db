package com.database.project.api.mapper;

import com.database.project.api.dto.CustomerRequestDTO;
import com.database.project.api.dto.CustomerResponseDTO;
import com.database.project.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDTO customerResponseDTO(Customer customer);

    List<CustomerResponseDTO> customerResponseDtoList(List<Customer> person);

    @Mapping(target = "id", ignore = true)
    Customer customer(CustomerRequestDTO customerRequestDTO);
}
