package coroutines.devcop.ii_channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.client.singletonHttpWorkClient
import util.trace
import util.withExecutionTime

private suspend fun sendValue(channel: SendChannel<Int>) = coroutineScope {
    trace("Getting a value from a remote service")
    val value = singletonHttpWorkClient.getValue()
    trace("Got value $value")
    channel.send(value)
    trace("Value $value sent")
}

private suspend fun receiveValues(channel: ReceiveChannel<Int>) = coroutineScope {
    trace("Receiving values from channel")
    for (item in channel) {
        trace("Received $item")
    }
}

fun main() = withExecutionTime {
    runBlocking {
        val channel = Channel<Int>()

        launch {
            trace("Sending values")
            repeat(5) {
                sendValue(channel)
            }
            channel.close()
        }

        launch {
            trace("Receiving Values")
            receiveValues(channel)
        }
    }
    trace("Done!")
}
