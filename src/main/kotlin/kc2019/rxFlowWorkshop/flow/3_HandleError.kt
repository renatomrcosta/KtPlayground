package kc2019.rxFlowWorkshop.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() {
    // TODO: Create a flow that emits three Stooge strings.
    val flow = flowOf("larry", "Curly", "Moe", "Jonesy", "tic", "tac")

    // TODO: Collect the Stooge strings and print each of their fifth letters to the console.
    runBlocking {

        flow
            .map { it -> it[4] }
            .catch { e ->
                when (e) {
                    is IndexOutOfBoundsException -> println("Outta bounds!")
                    else -> println("FUCK!")
                }
            }
            .collect {
                println(it)
            }
    }
}
