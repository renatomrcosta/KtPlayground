package coroutines.devcop.iii_flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import util.client.WorkClient
import util.trace
import util.withExecutionTime

private fun myFlow(): Flow<Int> = flow {
    trace("Flow has started")
    for (i in 1..1000) {
        val value = WorkClient.getValue()
        trace("Executed Job #$i | Result: $value")
        emit(value)
    }
}.flowOn(Dispatchers.Default)

fun main() = withExecutionTime {
    runBlocking {
        trace("Calling the Flow builder")
        val flow = myFlow()

        flow
            .buffer(10)
            .collect {
                delay(100)
                trace("Result $it processed")
            }
    }
}
