package kc2019.rxFlowWorkshop.solution

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

private const val FREEZING_POINT_KELVIN = 273.15

// Add an extension function to Flow<Int> that filters out numbers below the freezing point
//  of water in Kelvin.
private fun Flow<Int>.filterIfBelowFreezing() = filter { temperature ->
    temperature <= FREEZING_POINT_KELVIN
}

fun main() {
    // Create a flow that emits the numbers 1 through 1000.
    val numbersFlow = flow {
        for (i in 1..1000) {
            emit(i)
        }
    }

    // Apply your new extension function to the flow you created.
    // Collect the integers emitted and print them to the console.
    runBlocking {
        numbersFlow
            .filterIfBelowFreezing()
            .collect { number ->
                println(number)
            }
    }
}
