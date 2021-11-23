package coroutines.devcop.ii_channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.client.WorkClient
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking {
        val channel = Channel<Int>()
        launch {
            var x = 1
            while (isActive) {
                trace("Getting a value from a remote service")
                val value = WorkClient.getValue()
                trace("Sending value $value")
                channel.send(value)
                trace("Value $value sent")
                x++
            }
        }
        trace("Launched channel. No one receives it")

    }
}
