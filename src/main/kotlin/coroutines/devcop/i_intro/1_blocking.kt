package coroutines.devcop.i_intro

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking {
        List(1_000_000) {
            launch {
                delay(1000)
                println("Executed Thread #$it")
            }
        }
    }
}


