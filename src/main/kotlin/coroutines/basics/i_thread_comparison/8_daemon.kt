package coroutines.basics.i_thread_comparison

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking {
        println("Starting Run Block")
        doBackgroundWork()
        doWork() // This will run sequentially
        println("Finished Run Block")
    }
    println("Program finished")
}

private fun CoroutineScope.doBackgroundWork() {
    launch {
        println("Starting background work")
        delay(500)
        println("Finishing background work")
    }
}

private suspend fun doWork() {
    println("Starting work")
    delay(100)
    println("Finishing work")
}
