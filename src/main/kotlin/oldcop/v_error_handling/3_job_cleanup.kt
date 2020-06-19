package oldcop.v_error_handling

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        runJob(startTime)
    }
    delay(1250)
    println("[${Thread.currentThread().name}] Waited a bit. Cancelling Job")
    job.cancelAndJoin()
    println("[${Thread.currentThread().name}] Job Cancelled")
}

suspend fun runJob(startTime: Long) = coroutineScope {
    try {
        var nextPrintTime = startTime
        while (isActive) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("[${Thread.currentThread().name}] Job is running!")
                nextPrintTime += 500
            }
        }
    } finally {
        println("[${Thread.currentThread().name}] The Job has ended")
    }
}
