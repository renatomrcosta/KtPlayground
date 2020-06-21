package coroutines.channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() {
    withExecutionTime {
        runBlocking {
            val channel = Channel<Int>()
            launch {
                for (x in 1..5) {
                    channel.send(x * x)
                }
            }
            println("Launched channel")
            repeat(5) {
                println(channel.receive())
            }
            println("done!")
        }
    }
}
