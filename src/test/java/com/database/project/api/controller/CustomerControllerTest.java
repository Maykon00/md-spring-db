package com.database.project.api.controller;

import com.database.project.api.dto.CustomerRequestDTO;
import com.database.project.api.dto.CustomerResponseDTO;
import com.database.project.domain.service.CustomerService;
import com.database.project.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    private CustomerResponseDTO responseDTO;

    private CustomerRequestDTO requestDTO;

    @BeforeEach
    void setUp(){
        responseDTO = new CustomerResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setName("test");
        responseDTO.setEmail("email@test");
        responseDTO.setAge(22);
        responseDTO.setCellPhone("123456789");

        requestDTO = new CustomerRequestDTO();
        requestDTO.setAge(22);
        requestDTO.setCellPhone("123456789");
        requestDTO.setName("teste");
        requestDTO.setEmail("teste@email");
    }

    @Test
    void getAll() {

        List<CustomerResponseDTO> list = new ArrayList<>();
        list.add(responseDTO);

        when(customerService.getAll()).thenReturn(list);
        ResponseEntity<List<CustomerResponseDTO>> result = customerController.getAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1L, result.getBody().get(0).getId());
        assertEquals("test", result.getBody().get(0).getName());
        assertEquals("email@test", result.getBody().get(0).getEmail());
        assertEquals(22, result.getBody().get(0).getAge());
        assertEquals("123456789", result.getBody().get(0).getCellPhone());

    }

    @Test
    void getAllNotFoundException() {
        when(customerService.getAll()).thenThrow(new NotFoundException(null, "test"));
        assertThrows(NotFoundException.class, () -> customerController.getAll());
    }

    @Test
    void findById() {

        when(customerService.get(1L)).thenReturn(responseDTO);
        ResponseEntity<CustomerResponseDTO> result = customerController.findById(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1L, result.getBody().getId());
    }

    @Test
    void findByIdNotFoudException() {
        when(customerService.get(1L)).thenThrow(new NotFoundException(1L, "test"));
        assertThrows(NotFoundException.class, () -> customerController.findById(1L));
    }

    @Test
    void post() {
        ResponseEntity<Void> result = customerController.post(requestDTO);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

    }

    @Test
    void update() {
        ResponseEntity<Void> result = customerController.update(1L, requestDTO);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void updateNotFoundException() {
        doThrow(new NotFoundException(1L, "test"))
                .when(customerService).update(1L, requestDTO);
        assertThrows(NotFoundException.class, () -> customerController.update(1L, requestDTO));
    }

    @Test
    void delete() {
        ResponseEntity<Void> result = customerController.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @Test
    void deleteNotFoundException() {
        doThrow(new NotFoundException(1L, "test"))
                .when(customerService).delete(1L);
        assertThrows(NotFoundException.class, () -> customerController.delete(1L));
    }
}