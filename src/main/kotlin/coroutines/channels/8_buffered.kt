package coroutines.channels

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

fun main() {
    withExecutionTime {
        runBlocking {
            val channel = Channel<Int>(capacity = 4)
            // Sender
            launch {
                repeat(10) {
                    trace("sending value $it")
                    channel.send(it)
                }
            }
            //Receiver
            launch {
                while (isActive) {
                    delay(100)
                    val value = channel.receive()
                    trace("received value $value")
                }
            }

            delay(1000)
            this.coroutineContext.cancelChildren()
        }
    }
}
