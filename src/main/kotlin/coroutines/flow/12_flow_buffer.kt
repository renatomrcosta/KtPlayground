package coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

private fun myFlow() = flow<Int> {
    for (i in 1..5) {
        delay(100)
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
                    delay(100)
                    println("Collecting $it")
                }
        }
    }
}
