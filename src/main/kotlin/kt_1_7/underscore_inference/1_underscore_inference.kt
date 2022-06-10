package kt_1_7.underscore_inference

interface MyWorker<T> {
    fun doWork(): T
}

class StringWorker : MyWorker<String> {
    override fun doWork(): String = "aaaa"
}

class IntWorker : MyWorker<Int> {
    override fun doWork(): Int = 42
}

inline fun <reified S: MyWorker<T>, T> doSomething(): T {
    return S::class.java.getDeclaredConstructor().newInstance().doWork()
}

fun main() {
    // 1.7 website example
    val s = doSomething<MyWorker<String>, _>()

    // Feels like a feature needed for large abstraction thingies. Normally shouldn't be a big usecase unless there's some specialized repetition of supertypes
}
