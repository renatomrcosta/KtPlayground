package coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
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
                .conflate() // Gets always the latest value
                .collect {
                    delay(500)
                    println("Collecting $it")
                }
        }
    }
}
