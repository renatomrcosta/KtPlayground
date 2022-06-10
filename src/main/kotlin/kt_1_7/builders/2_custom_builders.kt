package kt_1_7.builders

data class Person(val id: Int)

class MyDataStruct {
    private val _people: MutableSet<Person> = mutableSetOf()

    fun addPerson(person: Person) {
        _people.add(person)
    }

    fun addPeople(people: Collection<Person>) {
        _people.addAll(people)
    }
    fun listAll() = _people.toList()
}

fun buildMyDataStruct(builder: MutableSet<Person>.() -> Unit): MyDataStruct = MyDataStruct().apply {
    addPeople(
        people = mutableSetOf<Person>().apply(builder)
    )
}

fun main() {
    val myStruct = buildMyDataStruct {
        add(Person(1))
        add(Person(2))
    }
    myStruct.listAll().forEach { println(it) }
}
