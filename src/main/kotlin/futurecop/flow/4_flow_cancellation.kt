package futurecop.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

private fun myFlow(): Flow<Int> = flow {
    println("Flow has started")
    for (i in 1..5) {
        delay(100)
        println("emitting index $i")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    println("Calling the Flow builder")
    val flow = myFlow()

    println("Calling the Flow collect")
    withTimeoutOrNull(250) {
        flow.collect { println(it) }
    }
}
