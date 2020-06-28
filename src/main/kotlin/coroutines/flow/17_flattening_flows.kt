package coroutines.flow

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

private fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500) // wait 500 ms
    emit("$i: Second")
}

@FlowPreview
fun main() {
    withExecutionTime {
        runBlocking {
            println("Usual Map")
            (1..3).asFlow().map { requestFlow(it) }.collect {
                it.collect {
                    println(it)
                }
            }
        }
    }

    withExecutionTime {
        runBlocking {
            println("Flat Map Concat")
            val startTime = System.currentTimeMillis()
            (1..3).asFlow().onEach { delay(100) }
                .flatMapConcat { requestFlow(it) }
                .collect {
                    println("$it done at ${System.currentTimeMillis() - startTime}ms after start time")
                }
        }
    }

    withExecutionTime {
        runBlocking {
            println("Flat Map Merge")
            val startTime = System.currentTimeMillis()
            (1..3).asFlow().onEach { delay(100) }
                .flatMapMerge { requestFlow(it) }
                .collect {
                    println("$it done at ${System.currentTimeMillis() - startTime}ms after start time")
                }
        }
    }
}
