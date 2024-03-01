package com.esg.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esg.entities.Customer;
import com.esg.records.CustomerRecord;
import com.esg.repos.CustomerRepository;

import jakarta.transaction.Transactional;

@RestController
// @RequestMapping(value = "/api/customer/${reference}", produces = "application/json", consumes = "application/json")
public class RestAppController {

    private final CustomerRepository customerRepository;

    public RestAppController(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/api/customer/{reference}")
    public ResponseEntity<CustomerRecord> getCustomer(@PathVariable final String reference) {
        try {
            final var customer = customerRepository.naturalId(reference).orElseThrow();
            return ResponseEntity.ok(customer.getCustomerRecord());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/customer/{reference}")
    @Transactional
    public ResponseEntity<CustomerRecord> postCustomer(@PathVariable final String reference,
                                                       @RequestBody final CustomerRecord customerRecord) {
        
        
        if(customerRepository.naturalId(reference).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final Customer newCustomer = new Customer(reference, customerRecord);

        return ResponseEntity.ok(customerRepository.save(newCustomer).getCustomerRecord());
    }


}
