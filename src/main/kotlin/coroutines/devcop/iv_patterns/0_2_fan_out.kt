package coroutines.devcop.iv_patterns

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// THis is the essential implementation without the produce API
private fun CoroutineScope.createProducerManual(): ReceiveChannel<Int> {
    val channel = Channel<Int>()
    launch {
        var x = 1
        while (isActive) {
            channel.send(x++)
        }
    }
    return channel
}

@OptIn(ExperimentalCoroutinesApi::class)
private fun CoroutineScope.createProducer(): ReceiveChannel<Int> = produce<Int> {
    var x = 1
    while (isActive) {
        send(x++)
    }
}

private fun CoroutineScope.startProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel) {
        println("Processor number $id received $msg")
    }
}

fun main() = runBlocking {
    val channel = createProducer()
    repeat(2) {
        startProcessor(it, channel)
    }
}
