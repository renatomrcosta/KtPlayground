package kc2019.rxFlowWorkshop.solution

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() {
    // Create a flow that emits three Stooge strings.
    val stoogesFlow: Flow<String> = flowOf(
        "Larry",
        "Moe",
        "Curly"
    )

    // Collect the Stooge strings and print each of their fifth letters to the console.
    // Handle any out of bounds exceptions.
    // Print out a message when your flow completes.
    runBlocking {
        try {
            stoogesFlow
                .map { stoogeName -> stoogeName[4] }
                .collect { letter ->
                    println(letter)
                }
        } catch (e: StringIndexOutOfBoundsException) {
            println(e)
        } finally {
            println("All done!")
        }
    }
}
