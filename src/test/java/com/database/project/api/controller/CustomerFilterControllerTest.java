package com.database.project.api.controller;

import com.database.project.api.dto.CustomerGroupByAgeDTO;
import com.database.project.api.dto.CustomerResponseDTO;
import com.database.project.domain.service.CustomerFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerFilterControllerTest {

    @InjectMocks
    private CustomerFilterController customerFilterController;

    @Mock
    private CustomerFilterService customerFilterService;

    private List<CustomerResponseDTO> customerResponseList;
    private List<CustomerGroupByAgeDTO> customerGroupByAgeList;

    @BeforeEach
    void setUp() {
        CustomerResponseDTO customer1 = new CustomerResponseDTO();
        customer1.setId(1L);
        customer1.setName("test");
        customer1.setAge(25);
        customer1.setEmail("test@email.com");
        customer1.setCellPhone("99999");

        customerResponseList = List.of(customer1);

        CustomerGroupByAgeDTO group25 = new CustomerGroupByAgeDTO(25, List.of(customer1));

        customerGroupByAgeList = List.of(group25);
    }

    @Test
    void filterByAge() {
        int age = 25;
        List<CustomerResponseDTO> expected = customerResponseList.stream()
                .filter(c -> c.getAge() == age)
                .toList();

        when(customerFilterService.filterAge(age)).thenReturn(expected);

        ResponseEntity<List<CustomerResponseDTO>> result = customerFilterController.filterByAge(age);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void groupCustomersByAge() {

        when(customerFilterService.groupCustomersByAge()).thenReturn(customerGroupByAgeList);
        ResponseEntity<List<CustomerGroupByAgeDTO>> result = customerFilterController.groupCustomersByAge();
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }
}

