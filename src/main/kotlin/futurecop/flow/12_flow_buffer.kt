package futurecop.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

private fun myFlow() = flow<Int> {
    for (i in 1..5) {
        delay(250)
        println("Emitting $i")
        emit(i)
    }
}

fun main() {
    withExecutionTime {
        runBlocking {
            myFlow()
                .buffer()
                .collect {
                    delay(500)
                    println("Collecting $it")
                }
        }
    }
}
