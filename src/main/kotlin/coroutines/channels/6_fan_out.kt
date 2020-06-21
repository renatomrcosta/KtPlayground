package coroutines.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

// All functions that create coroutines are defined as extensions on CoroutineScope,
// so that we can rely on structured concurrency
// to make sure that we don't have lingering global coroutines in our application.
@ExperimentalCoroutinesApi
private fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true) {
        send(x++)
        delay(100)
    }
}

@ExperimentalCoroutinesApi
private fun CoroutineScope.launchProcessor(id: Int, channelToProcess: ReceiveChannel<Int>) = launch {
    for (msg in channelToProcess) {
        println("Processor number $id received $msg")
    }
}

@ExperimentalCoroutinesApi
fun main() {
    withExecutionTime {
        runBlocking {
            val numbers = produceNumbers()
            repeat(5) {
                launchProcessor(it, numbers)
            }
            delay(950)
            println("Done")
            numbers.cancel()
        }
    }
}
