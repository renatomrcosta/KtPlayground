package kc2019.rxFlowWorkshop.flow

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {
    // TODO: Create a flow that emits three Stooge strings.
    val flow = flowOf("Larry", "Moe", "Curly")

    // TODO: Collect the Stooge strings and print them to the console.

    runBlocking {
        flow.collect { stooge ->
            println(stooge)
        }
    }
}
