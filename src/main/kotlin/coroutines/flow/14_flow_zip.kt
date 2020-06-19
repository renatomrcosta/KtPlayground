package coroutines.flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() {
    withExecutionTime {
        runBlocking {
            val numericalFlow = (1..3).asFlow()
            val stringFlow = flowOf("one", "two", "three")

            numericalFlow.zip(stringFlow) { a, b -> "$a means $b" }
                .collect { println(it) }
        }
    }
}
