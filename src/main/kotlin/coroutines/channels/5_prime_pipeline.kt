package coroutines.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

// All functions that create coroutines are defined as extensions on CoroutineScope,
// so that we can rely on structured concurrency
// to make sure that we don't have lingering global coroutines in our application.
@ExperimentalCoroutinesApi
private fun CoroutineScope.produceNumbersFrom(start: Int = 1) = produce {
    var x = start
    while (true) {
        send(x++)
    }
}

@ExperimentalCoroutinesApi
private fun CoroutineScope.filterDivisible(numbers: ReceiveChannel<Int>, divisor: Int) = produce {
    for (x in numbers) {
        if (x % divisor != 0) {
            send(x)
        }
    }
}

@ExperimentalCoroutinesApi
fun main() {
    withExecutionTime {
        runBlocking {
            var numbers = produceNumbersFrom(2)
            repeat(200) {
                val prime = numbers.receive()
                println("Trying $prime")
                numbers = filterDivisible(numbers, prime)
            }
            println("Done")

            //Cancels running jobs, if they are not yet done here
            coroutineContext.cancelChildren()
        }
    }
}
