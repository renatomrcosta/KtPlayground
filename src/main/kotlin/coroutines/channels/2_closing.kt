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
                channel.close()
            }
            println("Launched channel")
            for (item in channel) {
                println(item)
            }
            println("done!")
        }
    }
}
