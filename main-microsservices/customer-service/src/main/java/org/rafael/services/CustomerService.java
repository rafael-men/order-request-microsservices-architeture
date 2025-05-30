package org.rafael.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.rafael.dto.CustomerDto;
import org.rafael.models.CustomerModel;
import org.rafael.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> findAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> new CustomerDto(
                        customer.getName(),
                        customer.getPhone(),
                        customer.getEmail(),
                        customer.getAddress(),
                        customer.getAge()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public CustomerDto createCustomer(CustomerDto dto) {
        CustomerModel customer = new CustomerModel();
        customer.setName(dto.getName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());
        customer.setAge(dto.getAge());

        customerRepository.persist(customer); 

        return new CustomerDto(
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getAge()
        );
    }

    public void updateCustomer(Long id, CustomerDto dto) {
        CustomerModel customer = customerRepository.findById(id);
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        customer.setAge(dto.getAge());

        customerRepository.persist(customer);
    }
}