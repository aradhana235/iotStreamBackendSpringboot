package com.alphacore.controller;

import com.alphacore.model.Customer;
import com.alphacore.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // GET ALL
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(
            @PathVariable UUID id
    ) {
        return customerRepository.findById(id);
    }

    // CREATE
    @PostMapping
    public Customer createCustomer(
            @RequestBody Customer customer
    ) {
        return customerRepository.save(customer);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Customer updateCustomer(
            @PathVariable UUID id,
            @RequestBody Customer updatedCustomer
    ) {

        return customerRepository.findById(id)
                .map(customer -> {

                    customer.setTitle(updatedCustomer.getTitle());
                    customer.setEmail(updatedCustomer.getEmail());
                    customer.setPhone(updatedCustomer.getPhone());
                    customer.setZip(updatedCustomer.getZip());

                    return customerRepository.save(customer);

                }).orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id " + id
                        )
                );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteCustomer(
            @PathVariable UUID id
    ) {

        customerRepository.deleteById(id);

        return "Customer deleted successfully";
    }
}