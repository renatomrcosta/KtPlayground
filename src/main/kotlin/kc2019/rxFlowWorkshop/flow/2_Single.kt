package kc2019.rxFlowWorkshop.flow

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main() {
    // TODO: Create a flow that emits an integer.

    val flow = flow {
        emit(42)
    }
    // TODO: Collect the integer and print it to the console.
    runBlocking {
        flow.collect { randomInt ->
            println(randomInt)
        }
    }
}
