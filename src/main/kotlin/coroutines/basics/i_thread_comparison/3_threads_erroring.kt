package coroutines.basics.i_thread_comparison

import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() {
    val timeMeasuredInMillis = measureTimeMillis {
        List(100) {
            thread {
                Thread.sleep(1000)
                if(it == 42) {
                    throw Exception("Something is Funky!")
                }
                println("Executed Thread #$it")
            }
        }.forEach {
            it.join()
        }
    }

    println("Execution took ${timeMeasuredInMillis}ms")
}
