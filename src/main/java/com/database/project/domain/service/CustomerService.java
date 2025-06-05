package com.database.project.domain.service;

import com.database.project.api.dto.CustomerRequestDTO;
import com.database.project.api.dto.CustomerResponseDTO;
import com.database.project.api.mapper.CustomerMapper;
import com.database.project.domain.model.Customer;
import com.database.project.exception.NotFoundException;
import com.database.project.infrastructure.repository.CustomerRepository;
import com.database.project.infrastructure.repository.ICustomer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomer {

    private final CustomerRepository repository;

    private final CustomerMapper mapper;

    public CustomerService(final CustomerRepository repository, final CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public CustomerResponseDTO get(Long id) {
        if(!repository.existsById(id)){
            throw new NotFoundException(id, "Id not found");
        }
        return mapper.customerResponseDTO(repository.getReferenceById(id));
    }

    @Override
    public List<CustomerResponseDTO> getAll() {
        List<CustomerResponseDTO> listCustomer = mapper.customerResponseDtoList(repository.findAll());
        if(listCustomer.isEmpty()){
            throw new NotFoundException(null, "No customers found");
        }
        return listCustomer;
    }

    @Override
    public void post(CustomerRequestDTO requestDto) {
        repository.save(mapper.customer(requestDto));
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new NotFoundException(id, "Id does not exist");
        }
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, CustomerRequestDTO request) {
        if(!repository.existsById(id)){
            throw new NotFoundException(id, "Id not found");
        }
        Customer person = mapper.customer(request);
        person.setId(id);
        repository.save(person);
    }
}
