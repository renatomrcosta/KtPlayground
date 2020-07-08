package coroutines.devcop.ii_channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking {
        val channel = Channel<Int>()

        trace("Sending values")
        launch {
            for (x in 1..5) {
                trace("Sending value $x")
                delay(100)
                channel.send(x * x)
                trace("Value $x sent")
            }
        }

        trace("Receiving Values")
        launch {
            trace("Receiving values from channel")
            for (item in channel) {
                trace(item)
            }
        }

        trace("done!")
    }
}
