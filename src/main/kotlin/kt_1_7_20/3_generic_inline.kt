package kt_1_7_20

@JvmInline
private value class Box<T>(val value: T)

@JvmInline
private value class Id(val value: Int)

fun main() {
    val id = Id(123)
    val something = Box("String")
    val somethingElse = Box(1)

    printClasses(something, somethingElse, id)
}

// Bytecode shows that the genertic types are boxed to the most common supertype. In this case, Object
//    public static final void printClasses_xBNzasI/* $FF was: printClasses-xBNzasI*/(@NotNull Object something, @NotNull Object somethingElse, int id) {
private fun printClasses(something: Box<String>, somethingElse: Box<Int>, id: Id) {
    println(something.value)
    println(somethingElse.value)
    println(id.value)
}
