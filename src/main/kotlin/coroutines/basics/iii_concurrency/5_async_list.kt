package coroutines.basics.iii_concurrency

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() =

    runBlocking {
        withExecutionTime {
            val fruitList = listOf(
                async { calculateBananas() },
                async { calculateApples() }
            )
            val sum = fruitList.awaitAll().sum()
            println("Sum: $sum")
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
