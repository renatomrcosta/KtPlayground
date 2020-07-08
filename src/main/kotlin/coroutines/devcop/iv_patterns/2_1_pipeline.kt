import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import util.trace

@ExperimentalCoroutinesApi
private fun CoroutineScope.createNumberProducer(): ReceiveChannel<Int> = produce<Int> {
    var x = 1
    while (isActive) {
        send(x++)
    }
}

@ExperimentalCoroutinesApi
private fun CoroutineScope.createSquaresProducer(numberChannel: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (number in numberChannel) {
        send(number * number)
    }
}

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    val numberProducer = createNumberProducer()
    val squaresProducer = createSquaresProducer(numberProducer)
    repeat(10) {
        trace(squaresProducer.receive())
    }
    trace("Done")
    this.coroutineContext.cancelChildren()
}
