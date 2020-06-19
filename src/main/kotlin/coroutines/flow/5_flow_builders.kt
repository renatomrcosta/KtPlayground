package coroutines.flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    println("flowOf builder")
    flowOf(1, 2, 3).collect { println(it) }

    println("asFlow builder")
    listOf(1, 2, 3).asFlow().collect { println(it) }
}
