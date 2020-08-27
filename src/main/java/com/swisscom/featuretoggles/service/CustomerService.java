package com.swisscom.featuretoggles.service;

import com.swisscom.featuretoggles.model.Customer;
import com.swisscom.featuretoggles.repository.CustomerRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer edit(Customer updatedCustomer, Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(updatedCustomer.getName());
                    return customerRepository.save(updatedCustomer);
                })
                .orElseGet(() -> {
                    updatedCustomer.setId(id);
                    return customerRepository.save(updatedCustomer);
                });
    }


    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

}
