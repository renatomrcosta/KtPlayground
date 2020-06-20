package kc2019.rxFlowWorkshop.solution

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {
    // Create a flow that emits an integer.
    val laidBackFlow: Flow<Int> = flow {
        emit(311)
    }

    // Collect the integer and print it to the console.
    runBlocking {
        laidBackFlow.collect { number ->
            println(number)
        }
    }
}
