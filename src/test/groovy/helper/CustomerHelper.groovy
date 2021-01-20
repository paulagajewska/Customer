package helper

import com.gdpr.model.persistance.Customer
import com.gdpr.controller.CustomerDTO

import java.time.LocalDate

class CustomerHelper {

    static Customer createSampleCustomer() {
         Customer customer = new Customer()
        customer.pesel = "90020164554"
        customer.firstName = "John"
        customer.lastName = "Smith"
        customer.dateOfBirth = new LocalDate(1990, 02, 01)
        customer.agreementForEmail = true
        customer.agreementForPersonalData = true
        customer.agreementForPhoneContact = true
        customer
    }

    static CustomerDTO createSampleCustomerDTO() {
        CustomerDTO dto = new CustomerDTO()
        dto.pesel = "90020164554"
        dto.firstName = "John"
        dto.lastName = "Smith"
        dto.dateOfBirth = new LocalDate(1990, 02, 01)
        dto.agreementForEmail = true
        dto.agreementForPersonalData = true
        dto.agreementForPhoneContact = true
        dto
    }
}
