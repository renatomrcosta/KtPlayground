package coroutines.basics.i_thread_comparison

import util.trace
import util.withExecutionTime
import kotlin.concurrent.thread

fun main() {
    // This block will execute, and the execution time will be printed out in milliseconds
    withExecutionTime {
        // A list with a number of items will spawn. Each item will initialize using the block of code below
        List(100) {
            thread {
                Thread.sleep(1000)
                trace("Executed Thread #$it")
            }
        }.onEach {
            // Wait until the thread spawned is done working before proceeding
            it.join()
        }
    }
}
