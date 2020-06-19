package coroutines.flow

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

private fun myIntegerFlow() = flow<Int> {
    for (i in 1..10) {
        println("emitting value $i")
        emit(i)
    }
}

fun main() {
    withExecutionTime {
        runBlocking {
            myIntegerFlow()
                .take(4)
                .map { it * 2 }
                .collect {
                    println("Collected value $it")
                }
        }
    }
}
