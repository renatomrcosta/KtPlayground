package kc2019.rxFlowWorkshop.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

private const val FREEZING_POINT_KELVIN = 273.15

// TODO: Add an extension function to Flow<Int> that filters out numbers below the freezing point
//  of water in Kelvin.
private fun Flow<Int>.filterIfBelowKelvinFreezingPoint() = this.filter {
    it >= FREEZING_POINT_KELVIN
}

fun main() {
    // TODO: Create a flow that emits the numbers 1 through 1000.
    val flow = flow {
        (1..1000).forEach {
            emit(it)
        }
    }

    // TODO: Apply your new extension function to the flow you created.
    // TODO: Collect the integers emitted and print them to the console.

    runBlocking {
        flow
            .filterIfBelowKelvinFreezingPoint()
            .collect { number ->
                println(number)
            }
    }
}
