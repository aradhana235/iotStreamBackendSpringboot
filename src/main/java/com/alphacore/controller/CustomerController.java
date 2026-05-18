package com.alphacore.controller;

import com.alphacore.model.Customer;
import com.alphacore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    // GET ALL CUSTOMERS
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // GET CUSTOMER BY ID
    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id) {
        return customerRepository.findById(id);
    }

    // CREATE CUSTOMER
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    // UPDATE CUSTOMER
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id,
                                   @RequestBody Customer updatedCustomer) {

        return customerRepository.findById(id).map(customer -> {

            customer.setTitle(updatedCustomer.getTitle());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPhone(updatedCustomer.getPhone());
            customer.setZip(updatedCustomer.getZip());

            return customerRepository.save(customer);

        }).orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    // DELETE CUSTOMER
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable String id) {
        customerRepository.deleteById(id);
        return "Customer deleted successfully with id " + id;
    }
}