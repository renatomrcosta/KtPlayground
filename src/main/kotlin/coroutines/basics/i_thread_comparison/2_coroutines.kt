package coroutines.basics.i_thread_comparison

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        val timeMeasuredInMillis = measureTimeMillis { // This block will execute, and the execution time will be printed out in milliseconds
            List(100_000) { // A list with a number of items will spawn. Each item will initialize using the block of code below
                launch {
                    delay(1000)
                    println("Executed Job #$it")
                }
            }.forEach {
                it.join() // Try to wait until all threads are done working
            }
        }
        println("Execution took ${timeMeasuredInMillis}ms")
    }
}
