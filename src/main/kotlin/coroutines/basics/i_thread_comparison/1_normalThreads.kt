package coroutines.basics.i_thread_comparison

import util.trace
import util.withExecutionTime
import kotlin.concurrent.thread

fun main() {
    withExecutionTime { // This block will execute, and the execution time will be printed out in milliseconds
        List(100) { // A list with a number of items will spawn. Each item will initialize using the block of code below
            thread {
                Thread.sleep(1000)
                trace("Executed Thread #$it")
            }
        }.forEach {
            it.join() // Wait until the thread spawned is done working before proceeding
        }
    }
}
