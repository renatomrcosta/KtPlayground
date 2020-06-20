package kc2019.rxFlowWorkshop.solution

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {
    // Create a flow that emits three Stooge strings.
    val stoogesFlow: Flow<String> = flowOf(
        "Larry",
        "Moe",
        "Curly"
    )

    // Collect the Stooge strings and print them to the console.
    runBlocking {
        stoogesFlow.collect { stooge ->
            println(stooge)
        }
    }
}
