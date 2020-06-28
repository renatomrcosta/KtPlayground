package coroutines.basics.iv_context

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import util.withExecutionTime

private fun myBlockingClientCall() {
    log("Starting blocking function call")
    Thread.sleep(3000)
    log("Finished blocking function call")
}

private suspend fun myNonBlockingClientCall(id: Int) {
    log("Starting NON blocking function call ID: $id")
    delay(2000)
    log("Finished NON blocking function call ID: $id")
}

fun main() = withExecutionTime {
    runBlocking {
        launch {
            myNonBlockingClientCall(1)
        }
        withContext(Dispatchers.IO) {
            myBlockingClientCall()
        }
        launch {
            myNonBlockingClientCall(2)
        }
    }
}

private fun getThreadName(): String = Thread.currentThread().name
private fun log(msg: String) = println("[${getThreadName()}] $msg")
