package current.tenant.issue

import grails.gorm.multitenancy.Tenants
import grails.test.mixin.integration.Integration
import grails.transaction.Rollback
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

/**
 * Created by goran on 2016-09-11.
 */
@Rollback
@Integration(applicationClass = Application)
class CurrentTenantSpec extends Specification {

    @Autowired
    PersonService personService

    void "test current tenant"() {
        when:
        def person1 = personService.createPersonWithCar(1, "Lisa", "Long", "Volvo", "XC60")
        def person2 = personService.createPersonWithCar(2, "Lisa", "Long", "Volvo", "XC70")
        def person3 = personService.createPersonWithCar(3, "Lisa", "Long", "Volvo", "XC90")

        then:
        person1.tenantId == 1L
        person1.cars.size() == 1
        person1.oneCar.toString() == "Volvo XC60"

        person2.tenantId == 2L
        person2.cars.size() == 1
        person2.oneCar.toString() == "Volvo XC70"

        person3.tenantId == 3L
        person3.cars.size() == 1
        person3.oneCar.toString() == "Volvo XC90"

        and:
        Tenants.withId(1L) { Person.count() == 1 }
        Tenants.withId(2L) { Person.count() == 1 }
        Tenants.withId(3L) { Person.count() == 1 }

        and:
        Tenants.withoutId { Person.count() == 3 }
    }
}
