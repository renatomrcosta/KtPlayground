package coroutines.devcop.iv_patterns

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.client.singletonHttpWorkClient
import util.trace
import util.withExecutionTime

private suspend fun doWork(workerId: Int, value: Int) = coroutineScope {
    trace("Worker $workerId -> Starting work with value $value")
    singletonHttpWorkClient.doRemoteWork(1000)
    trace("Worker $workerId -> Ending work with value $value")
}

private suspend fun startWorker(workerId: Int, channel: ReceiveChannel<Int>) {
    for (item in channel) {
        doWork(workerId = workerId, value = item)
    }
}

private const val NUMBER_OF_WORKERS = 8

fun main() =
    runBlocking {
        withExecutionTime {
            val channel = Channel<Int>()
            repeat(NUMBER_OF_WORKERS) {
                launch {
                    startWorker(channel = channel, workerId = it)
                }
            }
            for (i in 1..25) {
                channel.send(i)
            }
        }
        delay(15000)
        trace("Finishing app")
        this.coroutineContext.cancelChildren()
    }

