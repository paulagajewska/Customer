package UT.gdpr.controller

import com.gdpr.controller.CustomerController
import com.gdpr.controller.Validator
import com.gdpr.controller.CustomerMapper
import com.gdpr.service.CustomerService
import com.gdpr.model.persistance.Customer
import com.gdpr.controller.CustomerDTO
import helper.CustomerHelper
import org.springframework.http.HttpStatus
import spock.lang.Specification

class CustomerControllerTest extends Specification {

    private CustomerService serviceMock = Mock(CustomerService)
    private CustomerMapper mapper = new CustomerMapper()
    private Validator validator = new Validator()

    private CustomerController controller = new CustomerController(serviceMock, mapper, validator)

    def "should return HTTP CREATED when customer is added"() {
        given:
        CustomerDTO dto = CustomerHelper.createSampleCustomerDTO()
        Customer customer = CustomerHelper.createSampleCustomer()

        and:
        1 * serviceMock.save(customer) >> customer

        when:
        def result = controller.addCustomer(dto)

        then:
        result.statusCode == HttpStatus.CREATED
        result.body == dto
    }

    def "should return HTTP OK when customer is found by pesel"() {
        given:
        CustomerDTO dto = CustomerHelper.createSampleCustomerDTO()
        Customer customer = CustomerHelper.createSampleCustomer()

        and:
        1 * serviceMock.findByPesel(customer.pesel) >> Optional.of(customer)

        when:
        def result = controller.getCustomerByPesel(customer.pesel)

        then:
        result.statusCode == HttpStatus.OK
        result.body == dto
    }

    def "should return HTTP NOT FOUND when customer is not found by pesel"() {
        given:
        Customer customer = CustomerHelper.createSampleCustomer()

        and:
        1 * serviceMock.findByPesel(customer.pesel) >> Optional.empty()

        when:
        def result = controller.getCustomerByPesel(customer.pesel)

        then:
        result.statusCode == HttpStatus.NOT_FOUND
    }

}
