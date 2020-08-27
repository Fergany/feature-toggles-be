package com.swisscom.featuretoggles.controller;

import com.swisscom.featuretoggles.model.Customer;
import com.swisscom.featuretoggles.model.Feature;
import com.swisscom.featuretoggles.repository.CustomerRepository;
import com.swisscom.featuretoggles.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Customer API"})
@RestController
@RequestMapping(value = "/api/v1")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Create Customer")
    @PostMapping(value = "/customers")
    Customer add(@ApiParam(value = "Customer object store in database table") @RequestBody Customer customer) {
        return customerService.add(customer);
    }

    @ApiOperation(value = "Update Customer")
    @PutMapping(value = "/customers/{id}")
    Customer edit(
            @ApiParam(value = "Updated Customer object", required = true) @RequestBody Customer updatedCustomer,
            @ApiParam(value = "Customer Id to update customer object", required = true, example = "1") @PathVariable Long id) {
        return customerService.edit(updatedCustomer, id);
    }

    @ApiOperation(value = "List Customers")
    @GetMapping(value = "/customers")
    List<Customer> getAll() {
        return customerService.getAll();
    }
}
