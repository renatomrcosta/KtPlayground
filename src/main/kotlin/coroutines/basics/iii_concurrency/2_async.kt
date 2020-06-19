package coroutines.basics.iii_concurrency

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() =
    withExecutionTime {
        runBlocking {
            val amountOfBananas = async { calculateBananas() }
            val amountOfApples = async { calculateApples() }

            println("Total amount of fruit: " +
                "${amountOfApples.await() + amountOfBananas.await()}")
        }
    }

private suspend fun calculateBananas(): Int {
    delay(1000)
    return 7
}

private suspend fun calculateApples(): Int {
    delay(1000)
    return 15
}
