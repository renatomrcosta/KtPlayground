package kc2019.rxFlowWorkshop.solution

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private const val FREEZING_POINT_KELVIN = 273.15

private fun Flow<Int>.filterIfBelowFreezing() = filter { temperature ->
    temperature <= FREEZING_POINT_KELVIN
}

@ExperimentalCoroutinesApi
fun main() {
    // Create a flow that emits the numbers 1 through 1000.
    val numbersFlow = flow {
        for (i in 1..1000) {
            emit(i)
        }
    }

    // Call filterIfBelowFreezing on numbersFlow in the Dispatchers.IO CoroutineContext.
    // Collect numbersFlow in the Dispatchers.Default CoroutineContext and print its
    //  emissions to the console.
    runBlocking {
        withContext(Dispatchers.Default) {
            numbersFlow.filterIfBelowFreezing()
                .flowOn(Dispatchers.IO)
                .collect { number ->
                    println(number)
                }
        }
    }
}
