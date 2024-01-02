package recurse_gen

import util.withExecutionTime

// Compare non tail rec to tailrec
fun main() {
    withExecutionTime {
        println("Non tailrec")
        fibonacci(100).also { println(it) }
    }

    withExecutionTime {
        println("Tailrec")
        fibonacciTailrec(1000).also { println(it) }
    }
}

private fun fibonacci(n: Long): Long =
    when {
        n <= 1 -> n
        else -> fibonacci(n - 1) + fibonacci(n - 2)
    }

private tailrec fun fibonacciTailrec(
    n: Long,
    previousNumber: Long = 0,
    currentNumber: Long = 1,
): Long =
    when (n) {
        0L -> previousNumber
        1L -> currentNumber
        else -> fibonacciTailrec(n - 1, currentNumber, previousNumber + currentNumber)
    }
