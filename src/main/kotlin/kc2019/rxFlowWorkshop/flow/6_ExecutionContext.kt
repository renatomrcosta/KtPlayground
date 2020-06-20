package kc2019.rxFlowWorkshop.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

private const val FREEZING_POINT_KELVIN = 273.15

private fun Flow<Int>.filterIfBelowFreezing() = filter { temperature ->
    temperature <= FREEZING_POINT_KELVIN
}

@ExperimentalCoroutinesApi
fun main() {
    // TODO: Create a flow that emits the numbers 1 through 1000.
    val flow = flow {
        for (i in 1..1000) {
            emit(i)
        }
    }
    // TODO: Call filterIfBelowFreezing on numbersFlow in the Dispatchers.IO CoroutineContext.

    runBlocking {
        flow
            .flowOn(Dispatchers.IO)
            .filterIfBelowFreezing()
            .flowOn(Dispatchers.Default)
            .collect { values ->
                println(values)

            }
    }
    // TODO: Collect numbersFlow in the Dispatchers.Default CoroutineContext and print its
    //  emissions to the console.
}
