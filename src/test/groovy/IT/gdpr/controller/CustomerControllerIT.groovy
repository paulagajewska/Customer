package IT.gdpr.controller

import com.gdpr.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class CustomerControllerIT extends Specification {

    @Autowired
    private MockMvc mockMvc

    def "should return HTTP CREATED when customer is added"() {
        given:
        def json = """{"dateOfBirth": "1990-02-01",
                "firstName": "John",
                "lastName": "Smith",
                "pesel": "90020164552",
                "agreementForPersonalData": true,
                "agreementForPhoneContact": true,
                "agreementForEmail": true}
                }"""

        when:
        def result = mockMvc.perform(post(new URI("/api/customer"))
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))

        then:
        result.andExpect(status().isCreated())
    }

    def "should return HTTP BAD REQUEST when one of filed is missing in json"() {
        given:
        def json = """{"dateOfBirth": "1996-02-01",
                "firstName": "John",
                "lastName": "Smith",
                "agreementForPersonalData": true,
                "agreementForPhoneContact": true,
                "agreementForEmail": true}
                }"""

        when:
        def result = mockMvc.perform(post(new URI("/api/customer"))
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))

        then:
        result.andExpect(status().isBadRequest())
    }

    @Unroll
    def "should return HTTP BAD REQUEST when #error"() {
        given:
        def json = """{"dateOfBirth": "1990-02-01",
                "firstName": "John",
                "lastName": "Smith",
                "pesel": "$pesel",
                "agreementForPersonalData": true,
                "agreementForPhoneContact": true,
                "agreementForEmail": true}
                }"""

        when:
        def result = mockMvc.perform(post(new URI("/api/customer"))
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))

        then:
        result.andExpect(status().isBadRequest())

        where:
        pesel           || error
        90020164552343  || "pesel has more than 11 characters"
        90020164        || "pesel has less than 11 characters"
        "aa020164552"   || "pesel contains words characters"
        93020164552     || "pesel is noot compatible with date of birth"
    }


    def "should return HTTP OK when customer is found by pesel"(){
        given:
        def json = """{"dateOfBirth": "1990-02-01",
                "firstName": "John",
                "lastName": "Smith",
                "pesel": "90020164552",
                "agreementForPersonalData": true,
                "agreementForPhoneContact": true,
                "agreementForEmail": true}
                }"""

        and:
        mockMvc.perform(post(new URI("/api/customer/"))
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))

        when:
        def result = mockMvc.perform(get(new URI("/api/customer/90020164552"))
                .contentType(MediaType.APPLICATION_JSON))

        then:
        result.andExpect(status().isOk())
    }

    def "should return HTTP NOT FOUND when customer is not found by pesel"(){
        when:
        def result = mockMvc.perform(get(new URI("/api/customer/93020164552"))
                .contentType(MediaType.APPLICATION_JSON))

        then:
        result.andExpect(status().isNotFound())
    }




}
