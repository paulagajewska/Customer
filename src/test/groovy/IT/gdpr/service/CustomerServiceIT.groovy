package IT.gdpr.service

import com.gdpr.Application
import com.gdpr.service.CustomerService
import com.gdpr.model.persistance.Customer
import helper.CustomerHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = Application.class)
class CustomerServiceIT extends Specification {

    @Autowired
    private CustomerService service

    def "should save customer"() {
        given:
        Customer customer = CustomerHelper.createSampleCustomer()

        when:
        def result = service.save(customer)

        then:
        with(result) {
            it.pesel == customer.pesel
            it.firstName == customer.firstName
            it.lastName == customer.lastName
            it.dateOfBirth == customer.dateOfBirth
            it.agreementForPhoneContact == customer.agreementForPhoneContact
            it.agreementForPersonalData == customer.agreementForPersonalData
            it.agreementForEmail == customer.agreementForEmail
        }
    }

    def "should update customer with the same pesel"() {
        given:
        Customer customer = CustomerHelper.createSampleCustomer()
        service.save(customer)
        customer.setFirstName("Bob")

        when:
        def result = service.save(customer)

        then:
        with(result) {
            it.firstName == "Bob"
        }
    }

    def "should return customer by pesel"() {
        given:
        Customer customer = CustomerHelper.createSampleCustomer()
        service.save(customer)

        when:
        def result = service.findByPesel(customer.pesel)

        then:
        with(result.get()) {
            it.pesel == customer.pesel
            it.firstName == customer.firstName
            it.lastName == customer.lastName
            it.dateOfBirth == customer.dateOfBirth
            it.agreementForPhoneContact == customer.agreementForPhoneContact
            it.agreementForPersonalData == customer.agreementForPersonalData
            it.agreementForEmail == customer.agreementForEmail
        }
    }
}
