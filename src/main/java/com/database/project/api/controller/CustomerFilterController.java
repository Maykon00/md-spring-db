package com.database.project.api.controller;

import com.database.project.api.dto.CustomerGroupByAgeDTO;
import com.database.project.api.dto.CustomerResponseDTO;
import com.database.project.domain.service.CustomerFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/db/customer")
public class CustomerFilterController {

    private final CustomerFilterService customerFilterService;

    public CustomerFilterController(CustomerFilterService customerFilterService) {
        this.customerFilterService = customerFilterService;
    }

    @GetMapping(value = "/by-age/{age}")
    public ResponseEntity<List<CustomerResponseDTO>> filterByAge(@PathVariable(value = "age") int age){
        return ResponseEntity.ok(customerFilterService.filterAge(age));
    }

    @GetMapping(value = "/group-age")
    public ResponseEntity<List<CustomerGroupByAgeDTO>> groupCustomersByAge(){
        return ResponseEntity.ok(customerFilterService.groupCustomersByAge());
    }

}
