package current.tenant.issue

class Car {
    String make
    String model

    static belongsTo = [person: Person]

    String toString() {
        "$make $model"
    }
}