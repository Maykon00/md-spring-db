package com.database.project.domain.service;

import com.database.project.api.dto.CustomerGroupByAgeDTO;
import com.database.project.api.dto.CustomerResponseDTO;
import com.database.project.api.mapper.CustomerMapper;
import com.database.project.domain.model.Customer;
import com.database.project.infrastructure.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerFilterServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerFilterService customerFilterService;

    private List<Customer> customers;
    private List<CustomerResponseDTO> customerResponseDTOList;

    @BeforeEach
    void setUp() {

        customers = List.of(
                new Customer(1L, "test", 25, "test@email.com", "99999"),
                new Customer(2L, "testOne", 25, "onde@email.com", "88888"),
                new Customer(3L, "testTwo", 30, "two@email.com", "77777")
        );

        customerResponseDTOList = customers.stream()
                .map(c -> {
                    CustomerResponseDTO dto = new CustomerResponseDTO();
                    dto.setId(c.getId());
                    dto.setName(c.getName());
                    dto.setAge(c.getAge());
                    dto.setEmail(c.getEmail());
                    dto.setCellPhone(c.getCellPhone());
                    return dto;
                })
                .toList();
    }

    @Test
    void filterCustomersByAge() {

        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.customerResponseDtoList(customers)).thenReturn(customerResponseDTOList);

        List<CustomerResponseDTO> result = customerFilterService.filterAge(25);

        assertEquals(2, result.size());
    }

    @Test
    void groupCustomersByAge() {

        when(customerRepository.findAll()).thenReturn(customers);
        when(customerMapper.customerResponseDtoList(customers)).thenReturn(customerResponseDTOList);
        List<CustomerGroupByAgeDTO> result = customerFilterService.groupCustomersByAge();

        assertEquals(2, result.size());
    }
}
