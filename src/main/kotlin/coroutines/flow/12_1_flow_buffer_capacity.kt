package coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

private fun myFlow() = flow<Int> {
    for (i in 1..5) {
        println("Emitting $i")
        delay(300)
        emit(i)
    }
}

fun main() {
    withExecutionTime {
        runBlocking {
            myFlow()
                .buffer(1)
                .collect {
                    println("Statting collecting $it")
                    delay(1000)
                    println("Collecting $it")
                }
        }
    }
}
