package coroutines.devcop.iii_flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

private fun myFlow(): Flow<Int> = flow {
    repeat(3) {
        delay(100)
        emit(it)
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

