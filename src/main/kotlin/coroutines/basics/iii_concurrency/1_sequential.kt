package coroutines.basics.iii_concurrency

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() =
    withExecutionTime {
        runBlocking {
            val amountOfBananas = calculateBananas()
            val amountOfApples = calculateApples()

            println("Total amount of fruit: ${amountOfApples + amountOfBananas}")
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
