package com.gdpr.controller;

import com.gdpr.model.persistance.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer fromDTO(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setPesel(dto.getPesel());
        customer.setDateOfBirth(dto.getDateOfBirth());
        customer.setAgreementForEmail(dto.isAgreementForEmail());
        customer.setAgreementForPersonalData(dto.isAgreementForPersonalData());
        customer.setAgreementForPhoneContact(dto.isAgreementForPhoneContact());
        return customer;
    }

    public CustomerDTO toDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setPesel(customer.getPesel());
        customerDTO.setDateOfBirth(customer.getDateOfBirth());
        customerDTO.setAgreementForEmail(customer.isAgreementForEmail());
        customerDTO.setAgreementForPersonalData(customer.isAgreementForPersonalData());
        customerDTO.setAgreementForPhoneContact(customer.isAgreementForPhoneContact());
        return customerDTO;
    }
}
