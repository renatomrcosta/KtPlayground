package oldcop.i_thread_comparison

import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() {
    val timeMeasuredInMillis = measureTimeMillis { // This block will execute, and the execution time will be printed out in milliseconds
        List(100_000) { // A list with a number of items will spawn. Each item will initialize using the block of code below
            thread {
                Thread.sleep(1000)
                println("Executed Thread #$it")
            }
        }.forEach {
            it.join() // Try to wait until all threads are done working
        }
    }

    println("Execution took ${timeMeasuredInMillis}ms")
}
