package coroutines.devcop.iii_flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import util.client.WorkClient
import util.trace
import util.withExecutionTime

private fun myFlow(): Flow<Int> = flow {
    repeat(3) {
        val value = WorkClient.getValue()
        emit(value)
    }
}

fun main() =
    withExecutionTime {
        runBlocking(Dispatchers.IO) {
            trace("FLOWS GO HERE NOW")
            myFlow()
                .buffer()
                .collect {
                    delay(100)
                    println(it)
                }
        }
    }

