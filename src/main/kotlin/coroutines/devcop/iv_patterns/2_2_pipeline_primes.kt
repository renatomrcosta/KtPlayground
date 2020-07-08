import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import util.trace

@ExperimentalCoroutinesApi
private fun CoroutineScope.createNumberProducer(startFrom: Int): ReceiveChannel<Int> = produce<Int> {
    var x = startFrom
    while (isActive) {
        delay(1_000)
        trace("sending $x")
        send(x++)
    }
}

@ExperimentalCoroutinesApi
private fun CoroutineScope.createFilterProducer(
    numberChannel: ReceiveChannel<Int>,
    prime: Int,
    producerId: Int
): ReceiveChannel<Int> =
    produce {
        for (number in numberChannel) {
            trace("[$producerId] Comparing $number mod $prime")
            if (number % prime != 0) {
                send(number)
            }
        }
    }

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit>(Dispatchers.Default) {
    var primeProducer = createNumberProducer(2)
    repeat(30) { producerId ->
        val prime = primeProducer.receive()
        trace("Prime $prime")
        primeProducer = createFilterProducer(primeProducer, prime, producerId)
    }
    trace("Done")
    this.coroutineContext.cancelChildren()
}
