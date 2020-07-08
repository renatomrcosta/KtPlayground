package coroutines.devcop.iii_flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

private fun myFlow() = flow<Int> {
    for (i in 1..10) {
        delay(100)
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
                    println("Collecting $it")
                    delay(500)
                }
        }
    }
}
