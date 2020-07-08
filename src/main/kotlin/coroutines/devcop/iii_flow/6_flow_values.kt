package coroutines.devcop.iii_flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import util.trace
import util.withExecutionTime
import kotlin.random.Random

private val RNGesus = Random(42L)

private suspend fun getValueFromAnotherService(id: Int): Int = withContext(Dispatchers.IO) {
    Thread.sleep(RNGesus.nextLong(10, 200))
    id * id
}

private fun myFlow(): Flow<Int> = flow {
    trace("Flow has started")
    for (i in 1..1000) {
        val result = getValueFromAnotherService(i)
        trace("Executed Job #$i | Result: $result")
        emit(result)
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
