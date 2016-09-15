package current.tenant.issue

import grails.core.GrailsApplication
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired

class PersonService {

    @Autowired
    PersonHandler personHandler

    @Autowired
    private GrailsApplication grailsApplication;

    @CompileStatic
    Person createPersonWithCar(long tenant, String firstName, String lastName, String make, String model) {
        personHandler.createWithCar(tenant, firstName, lastName, make, model)
    }
}