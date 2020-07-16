package coroutines.devcop.iii_flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import util.client.singletonHttpWorkClient
import util.trace

private fun myFlow(): Flow<Int> = flow {
    trace("Flow has started")
    for (i in 1..3) {
        val value = singletonHttpWorkClient.getValue()
        trace("emitting value $value")
        emit(value)
    }
}

fun main() = runBlocking<Unit> {
    trace("Calling the Flow builder")
    val flow = myFlow()

    trace("Calling the Flow collect")
    flow.collect { trace("Collected value $it") }

    trace("Calling the Flow collect AGAIN")
    flow.collect { trace("Collected value $it") }
}
