package coroutines.devcop.iv_patterns

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace

private fun CoroutineScope.sendString(channel: SendChannel<String>, msg: String, time: Long) = launch {
    while (isActive) {
        delay(time)
        channel.send(msg)
    }
}

fun main() = runBlocking<Unit> {
    val channel = Channel<String>()
    sendString(channel, "foo", 200L)
    sendString(channel, "BAR", 500L)

    repeat(7) {
        trace(channel.receive())
    }
    coroutineContext.cancelChildren()
}
