package UT.gdpr.controller

import com.gdpr.controller.CustomerMapper
import com.gdpr.model.persistance.Customer
import com.gdpr.controller.CustomerDTO
import helper.CustomerHelper
import spock.lang.Specification

class CustomerMapperTest extends Specification {

    private CustomerMapper mapper = new CustomerMapper();

    def "should return correctly map to CustomerDTO"() {
        given:
        Customer customer = CustomerHelper.createSampleCustomer()

        expect:
        with(mapper.toDTO(customer)) {
            it.pesel == customer.pesel
            it.firstName == customer.firstName
            it.lastName == customer.lastName
            it.dateOfBirth == customer.dateOfBirth
            it.agreementForPhoneContact == customer.agreementForPhoneContact
            it.agreementForPersonalData == customer.agreementForPersonalData
            it.agreementForEmail == customer.agreementForEmail
        }
    }

    def "should return correctly map to Customer"() {
        given:
        CustomerDTO dto = CustomerHelper.createSampleCustomerDTO()

        expect:
        with(mapper.fromDTO(dto)) {
            it.pesel == dto.pesel
            it.firstName == dto.firstName
            it.lastName == dto.lastName
            it.dateOfBirth == dto.dateOfBirth
            it.agreementForPhoneContact == dto.agreementForPhoneContact
            it.agreementForPersonalData == dto.agreementForPersonalData
            it.agreementForEmail == dto.agreementForEmail
        }
    }
}
