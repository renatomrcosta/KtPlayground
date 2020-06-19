package coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

private fun myFlow(): Flow<Int> = flow {
    println("Flow has started")
    for (i in 1..3) {
        delay(100)
        println("emitting index $i")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    println("Calling the Flow builder")
    val flow = myFlow()

    println("Calling the Flow collect")
    flow.collect { println(it) }

    println("Calling the Flow collect AGAIN")
    flow.collect { println(it) }
}
