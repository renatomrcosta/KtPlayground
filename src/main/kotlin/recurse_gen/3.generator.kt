package recurse_gen

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import util.withExecutionTime

suspend fun main() {
    withExecutionTime {
        println("Fibo sequence is cold")
        val sequence = fiboSequence()
        val flowwww = fiboFlow()
    }

    withExecutionTime {
        println("collecting it")
        val sequence = fiboSequence()
        println(sequence.take(45).last())
    }

    withExecutionTime {
        println("flow collecting it")
        val flow = fiboFlow()
        println(flow.take(45).last())
    }
}


private fun fiboSequence() = sequence {
    var previousNumber = 0L
    var currentNumber = 1L
    var buffer: Long

    do {
        yield(currentNumber)
        buffer = currentNumber
        currentNumber += previousNumber
        previousNumber = buffer
    } while (true)
}

private fun fiboFlow() = flow {
    var previousNumber = 0L
    var currentNumber = 1L
    var buffer: Long

    do {
        emit(currentNumber)
        buffer = currentNumber
        currentNumber += previousNumber
        previousNumber = buffer
    } while (true)
}