package com.database.project.domain.service;

import com.database.project.api.dto.CustomerGroupByAgeDTO;
import com.database.project.api.dto.CustomerResponseDTO;
import com.database.project.api.mapper.CustomerMapper;
import com.database.project.infrastructure.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerFilterService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerFilterService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    //Filter by Age
    public List<CustomerResponseDTO> filterAge(int age){

        return Optional.ofNullable(customerMapper.customerResponseDtoList(customerRepository.findAll()))
                .orElse(Collections.emptyList())
                .stream().filter(customer -> customer.getAge() == age)
                .toList();
    }

    //Grouping by Age
    public List<CustomerGroupByAgeDTO> groupCustomersByAge() {
        Map<Integer, List<CustomerResponseDTO>> grouped = Optional.ofNullable(
                        customerMapper.customerResponseDtoList(customerRepository.findAll()))
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.groupingBy(CustomerResponseDTO::getAge));

        return grouped.entrySet().stream()
                .map(entry -> new CustomerGroupByAgeDTO(entry.getKey(), entry.getValue()))
                .toList();
    }

}
