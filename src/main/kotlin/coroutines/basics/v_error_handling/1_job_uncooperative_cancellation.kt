package coroutines.basics.v_error_handling

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch {
        var nextPrintTime = startTime
        while (true) {
            if(System.currentTimeMillis() >= nextPrintTime) {
                println("Job is running!")
                nextPrintTime += 500
            }
        }
    }
    delay(1250)
    println("Waited a bit. Cancelling Job")
    job.cancelAndJoin()
    println("Job Cancelled")
}
