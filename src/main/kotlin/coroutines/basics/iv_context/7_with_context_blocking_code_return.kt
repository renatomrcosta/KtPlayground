package coroutines.basics.iv_context

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import util.withExecutionTime

private fun myBlockingClientCall(): Int {
    log("Starting blocking function call")
    Thread.sleep(3000)
    log("Finished blocking function call")
    return 42
}

private suspend fun myNonBlockingClientCall(id: Int): Int {
    log("Starting NON blocking function call ID: $id")
    delay(2000)
    log("Finished NON blocking function call ID: $id")
    return 10 * id
}

fun main() = withExecutionTime {
    runBlocking {
        val block1 = async {
            myNonBlockingClientCall(1)
        }
        withContext(Dispatchers.IO) {
            println(myBlockingClientCall())
        }
        val block2 = async {
            myNonBlockingClientCall(2)
        }
        println(block1.await())
        println(block2.await())
    }
}

private fun getThreadName(): String = Thread.currentThread().name
private fun log(msg: String) = println("[${getThreadName()}] $msg")
