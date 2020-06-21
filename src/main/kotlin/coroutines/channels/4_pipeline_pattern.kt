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
private fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true) {
        send(x++)
    }
}

@ExperimentalCoroutinesApi
private fun CoroutineScope.square(numbers: ReceiveChannel<Int>) = produce {
    for (x in numbers) {
        send(x * x)
    }
}

@ExperimentalCoroutinesApi
fun main() {
    withExecutionTime {
        runBlocking {
            val numbers = produceNumbers()
            val squares = square(numbers)
            repeat(5) {
                println(squares.receive())
            }
            println("Done")

            //Cancels running jobs, if they are not yet done here
            coroutineContext.cancelChildren()
        }
    }
}
