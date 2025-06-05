package com.database.project.infrastructure.repository;

import com.database.project.api.dto.CustomerRequestDTO;
import com.database.project.api.dto.CustomerResponseDTO;

import java.util.List;

public interface ICustomer {

    CustomerResponseDTO get(Long id);

    List<CustomerResponseDTO> getAll();

    void post(CustomerRequestDTO requestDto);

    void delete(Long id);

    void update(Long id, CustomerRequestDTO request);
}
