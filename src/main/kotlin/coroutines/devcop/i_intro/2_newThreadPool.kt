package coroutines.devcop.i_intro

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking(newFixedThreadPoolContext(2, "pool")) {
        List(1_000_000) {
            launch {
                withContext(Dispatchers.IO) { Thread.sleep(100) }
                trace("Executed Job #$it")
            }
        }
    }
}


