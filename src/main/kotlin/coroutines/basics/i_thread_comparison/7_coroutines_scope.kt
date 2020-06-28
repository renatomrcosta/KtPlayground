package coroutines.basics.i_thread_comparison

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking {
        repeat(100_000) {
            launch {
                delay(1000)
                println("Executed Job #$it")
            }
        }
    }
}


