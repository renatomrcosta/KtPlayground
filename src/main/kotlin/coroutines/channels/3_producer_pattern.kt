package coroutines.channels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

@ExperimentalCoroutinesApi
private fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) {
        send(x * x)
    }
}

@ExperimentalCoroutinesApi
fun main() {
    withExecutionTime {
        runBlocking {
            val squares = produceSquares()
            squares.consumeEach {
                println(it)
            }
            println("Done")
        }
    }
}
