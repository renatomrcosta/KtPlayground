package coroutines.devcop.iii_flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import util.client.WorkClient
import util.trace

private fun myFlow(): Flow<Int> = flow {
    trace("Flow has started")
    for (i in 1..3) {
        val value = WorkClient.getValue()
        trace("emitting value $value")
        emit(value)
    }
}

fun main() = runBlocking<Unit> {
    trace("Calling the Flow builder")
    val flow = myFlow()

    trace("Done")
}
