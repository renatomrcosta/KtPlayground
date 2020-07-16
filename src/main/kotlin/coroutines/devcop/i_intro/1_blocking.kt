package coroutines.devcop.i_intro

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import util.client.singletonHttpWorkClient
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking(Dispatchers.Default) {
        List(1_000_000) {
            launch {
                withContext(Dispatchers.IO) {
                    singletonHttpWorkClient.doRemoteWork(100)
                }
                trace("Executed Job #$it")
            }
        }
    }
}


