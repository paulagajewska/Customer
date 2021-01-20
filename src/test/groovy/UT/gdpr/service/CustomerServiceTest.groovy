package UT.gdpr.service

import com.gdpr.service.CustomerService
import com.gdpr.model.persistance.Customer
import com.gdpr.model.persistance.repositories.CustomerRepository
import helper.CustomerHelper
import spock.lang.Specification

class CustomerServiceTest extends Specification {

    private CustomerRepository repositoryMock = Mock(CustomerRepository)
    private CustomerService service = new CustomerService(repositoryMock);

    def "should save customer"() {
        given:
        Customer customer = CustomerHelper.createSampleCustomer()
        1 * repositoryMock.save(_) >> customer

        when:
        def result = service.save(customer)

        then:
        result == customer
    }

    def "should return customer by pesel"() {
        given:
        Customer customer = CustomerHelper.createSampleCustomer()
         1 *repositoryMock.findByPesel(customer.pesel) >> Optional.of(customer)

        when:
        def result = service.findByPesel(customer.pesel)

        then:
        result.get() == customer
    }

    def "should return optional empty when customer is not found by pesel"() {
        given:
        1 *repositoryMock.findByPesel(_) >> Optional.empty()

        when:
        def result = service.findByPesel("11111111111")

        then:
        result.isEmpty()
    }
}
