package com.gdpr.service;

import com.gdpr.model.persistance.Customer;
import com.gdpr.model.persistance.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> findByPesel(String pesel) {
        return customerRepository.findByPesel(pesel);
    }
}
