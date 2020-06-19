package oldcop.iii_concurrency

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() =
    withExecutionTime {
        runBlocking {
            try {
                val sum = concurrentSum()
                println("Total amount of fruit: $sum")
            } catch (e: ArithmeticException) {
                println("Invalid maths happened")
            }
        }
    }

private suspend fun concurrentSum(): Int = coroutineScope {
    val amountOfBananas = async { calculateBananasForAReallyLongTime() }
    val amountOfApples = async { calculateApples() }

    amountOfApples.await() + amountOfBananas.await()
}

suspend fun calculateBananasForAReallyLongTime(): Int {
    try {
        delay(Long.MAX_VALUE)
        return 42
    } finally {
        println("Exiting calculateBananas function")
    }
}

private suspend fun calculateApples(): Int {
    delay(100)
    println("Error happened here")
    throw ArithmeticException()
}
