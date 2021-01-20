package UT.gdpr.controller

import com.gdpr.controller.DateOfBirthNotCompatibleException
import com.gdpr.controller.Validator
import com.gdpr.controller.CustomerDTO
import spock.lang.Specification

import java.time.LocalDate

class ValidatorTest extends Specification {

    private Validator validator = new Validator()

    def "should throw exception when dateOfBirth is not compatible with pesel"() {
        given:
        LocalDate dob = new LocalDate(1990, 02, 01)
        String pesel = "91020164552"

        and:
        CustomerDTO customer = Mock(CustomerDTO) {
            it.pesel >> pesel
            it.dateOfBirth >> dob
        }

        when:
        validator.validatePeselWithDateOfBirth(customer)

        then:
        thrown(DateOfBirthNotCompatibleException)
    }

    def "should not throw exception when dateOfBirth is compatible with pesel"() {
        given:
        LocalDate dob = new LocalDate(1990, 02, 01)
        String pesel = "90020164552"

        and:
        CustomerDTO customer = Mock(CustomerDTO) {
            it.pesel >> pesel
            it.dateOfBirth >> dob
        }

        when:
        validator.validatePeselWithDateOfBirth(customer)

        then:
        noExceptionThrown()
    }
}
