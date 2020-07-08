package coroutines.devcop.i_intro

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking(newFixedThreadPoolContext(2, "pool")) {
        List(1_000_000) {
            launch {
                delay(1000)
                // withContext(Dispatchers.IO) { Thread.sleep(100)}
                trace("Executed Job #$it")
            }
        }
    }
}


