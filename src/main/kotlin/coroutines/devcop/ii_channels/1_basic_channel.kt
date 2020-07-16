package coroutines.devcop.ii_channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.client.singletonHttpWorkClient
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking {
        val channel = Channel<Int>()

        trace("Sending values")
        launch {
            for (x in 1..5) {
                trace("Getting a value from a remote service")
                val value = singletonHttpWorkClient.getValue()
                trace("Sending value $value")
                channel.send(value)
                trace("Value $value sent")
            }
        }

        trace("Receiving Values")
        launch {
            trace("Receiving values from channel")
            for (item in channel) {
                trace("Received $item")
            }
        }

        trace("done!")
    }
}
