package current.tenant.issue

import grails.gorm.MultiTenant
import grails.gorm.multitenancy.Tenants

class Person implements MultiTenant<Person> {
    Long tenantId
    String firstName
    String middleName
    String lastName

    static hasMany = [cars: Car]

    static constraints = {
        firstName(unique: ['tenantId', 'lastName'])
        middleName(nullable: true)
    }

    static mapping = {
        cache true
    }

    static transients = ['oneCar']

    def beforeValidate() {
        if (tenantId == null) {
            tenantId = Tenants.currentId()
        }
    }

    private transient Car getOneCar() {
        cars?.find{it}
    }

    String toString() {
        "$firstName $lastName"
    }
}