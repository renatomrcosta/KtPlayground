package kt_1_7.def_nn

interface Interop<T> : JavaInterface<T> {
    // Notice that the @NotNull annotations generate the T & Any type definition directly
    override fun print(value: T & Any): T & Any {
        TODO("Not yet implemented")
    }

    // Whereas the nullability of the type is here still
    override fun work(value: T): T {
        TODO("Not yet implemented")
    }
}

// observe the bounds
interface ConcreteInterop : JavaInterface<String> {
    override fun print(value: String): String {
        TODO("Not yet implemented")
    }

    override fun work(value: String?): String {
        TODO("Not yet implemented")
    }
}

fun main() {
    val impl = object : Interop<String> {}
}
