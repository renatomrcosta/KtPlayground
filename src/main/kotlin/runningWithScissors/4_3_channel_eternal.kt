package runningWithScissors

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

@ExperimentalCoroutinesApi
private fun CoroutineScope.produceJobChannel() = produce {
    var iter = 0
    while (!this.isClosedForSend) {
        delay(1000) // Simulates going to do some work in the server for that particular ID or something
        send(iter++)
    }
}

@ExperimentalCoroutinesApi
fun main() = withExecutionTime {
    runBlocking(Dispatchers.Default) {
        trace("Producing Job channel")
        val channel = produceJobChannel()

        trace("Starting to receive a flow")
        channel
            .receiveAsFlow()
            .buffer()
            .collect {
                launch {
                    trace("ID = $it work block start")
                    delay(10_000)
                    trace("ID = $it work block finish")
                }
            }

        trace("Collection complete")
    }
}
