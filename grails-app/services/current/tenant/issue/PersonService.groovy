package current.tenant.issue

import grails.gorm.multitenancy.Tenants

class PersonService {

    Person getPerson(long tenant, String firstName, String lastName) {
        Tenants.withId(tenant) {
            Person.createCriteria().get {
                eq 'firstName', firstName
                eq 'lastName', lastName
            }
        } as Person
    }

    Person createPersonWithCar(long tenant, String firstName, String lastName, String make, String model) {
        def person = getPerson(tenant, firstName, lastName)
        Tenants.withId(tenant) {
            if (person == null) {
                person = new Person(firstName: firstName, lastName: lastName)
                person.addToCars(new Car(person: person, make: make, model: model))
                person.save(failOnError: true)
            }
            person
        } as Person
    }

    Person createPersonWithCarNested(long tenant, String firstName, String lastName, String make, String model) {
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