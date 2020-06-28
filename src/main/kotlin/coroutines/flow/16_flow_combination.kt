package coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import util.withExecutionTime
import kotlin.system.measureTimeMillis

fun main() {
    withExecutionTime {
        runBlocking {
            val numericalFlow = (1..3).asFlow().onEach { delay(300) }
            val stringFlow = flowOf("one", "two", "three").onEach { delay(400) }

            println("Zip Flow")
            val zipTiming = measureTimeMillis {
                numericalFlow.zip(stringFlow) { a, b -> "$a means $b" }
                    .collect { println(it) }
            }

            println("Combine Flow")
            val combineTiming = measureTimeMillis {
                numericalFlow.combine(stringFlow) { a, b -> "$a means $b" }
                    .collect { println(it) }
            }

            println("Timing using Zip: $zipTiming")
            println("Timing using Combine: $combineTiming")
        }
    }
}
