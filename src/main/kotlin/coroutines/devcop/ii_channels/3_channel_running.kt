package coroutines.devcop.ii_channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking {
        val channel = Channel<Int>()
        launch {
            var x = 1
            while (isActive) {
                trace("Sending value $x")
                delay(100)
                channel.send(x * x)
                trace("Value $x sent")
                x++
            }
        }
        trace("Launched channel. No one receives it")

    }
}
