package kt_1_7_20

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

private class Something {
    var newName: String = "1234"
    var oldName: String by this::newName

    val banana: String by lazy {
        println("Hehehey, banana ran!")
        "abc"
    }

    var stuffHandledClass: String by MyDelegateClass()
    val stuffHandledObject: String by MyDelegateObject
}

private class MyDelegateClass<Ref> : ReadWriteProperty<Ref, String> {
    override fun getValue(thisRef: Ref, property: KProperty<*>): String {
        println("Gets the value from the prop: $property, $thisRef")
        return "aaaa"
    }

    override fun setValue(thisRef: Ref, property: KProperty<*>, value: String) {
        println("Sets the value $property, $thisRef, $value")
    }
}

object MyDelegateObject {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("Got the value boy!")
        return "aaaaaaa"
    }
}

fun main() {
    with(Something()) {
        println("newName=$newName")
        println("oldName=$oldName")
        oldName = "aaa"
        println("oldName=$oldName")
        println("newName=$newName")
        stuffHandledClass = "123123"
        println("stuffHandledClass=$stuffHandledClass")
        println("stuffHandledObject=$stuffHandledObject")
        println("banana=$banana")
        println("banana=$banana")
    }
}
