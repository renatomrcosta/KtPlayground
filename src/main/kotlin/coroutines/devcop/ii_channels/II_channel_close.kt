package coroutines.devcop.ii_channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking {
        val channel = Channel<Int>()
        launch {
            for (x in 1..5) {
                println("Sending value $x")
                delay(100)
                channel.send(x * x)
                println("Value $x sent")
            }
        }
        println("Launched channel")
        for (item in channel) {
            println(item)
        }
        println("done!")
    }
}
