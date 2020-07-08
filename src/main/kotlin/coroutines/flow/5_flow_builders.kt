package coroutines.flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import util.trace

fun main() = runBlocking<Unit> {
    trace("flowOf builder")
    flowOf(1, 2, 3).collect { trace(it) }

    trace("asFlow builder")
    listOf(1, 2, 3).asFlow().collect { trace(it) }
}
