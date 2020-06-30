package runningWithScissors

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

@ExperimentalCoroutinesApi
private fun CoroutineScope.produceJobChannel(totalBatch: Int = 1) = produce {
    for (i in 1..totalBatch) {
        delay(100) // Simulates going to do some work in the server for that particular ID or something
        send(i)
    }
}

@ExperimentalCoroutinesApi
fun main() = withExecutionTime {
    runBlocking(Dispatchers.Default) {
        trace("Producing Job channel")
        val channel = produceJobChannel(100)

        trace("Starting to receive a flow")
        channel.receiveAsFlow().map {
            launch {
                trace("ID = $it work block start")
                delay(1000)
                trace("ID = $it work block finish")
            }
        }.collect()

        trace("Collection complete")
    }
}
