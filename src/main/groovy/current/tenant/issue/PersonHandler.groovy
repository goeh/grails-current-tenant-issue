package current.tenant.issue

import grails.gorm.multitenancy.Tenants
import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

/**
 * Created by goran on 2016-09-11.
 */
@Component
@CompileStatic
class PersonHandler {

    Person getPerson(long tenant, String firstName, String lastName) {
        Tenants.withId(tenant) {
            Person.createCriteria().get {
                eq 'firstName', firstName
                eq 'lastName', lastName
            }
        } as Person
    }

    Person createWithCar(long tenant, String firstName, String lastName, String make, String model) {
        Tenants.withId(tenant) {
            def person = getPerson(tenant, firstName, lastName)
            if (person == null) {
                person = new Person(firstName: firstName, lastName: lastName)
                person.addToCars(new Car(person: person, make: make, model: model))
                person.save(failOnError: true)
            }
            person
        } as Person
    }
}
