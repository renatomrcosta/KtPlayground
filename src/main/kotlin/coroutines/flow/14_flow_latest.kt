package coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

private fun myFlow() = flow<Int> {
    for (i in 1..10) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

fun main() {
    withExecutionTime {
        runBlocking {
            myFlow()
                .collectLatest {
                    delay(300)
                    println("Collecting $it")
                }
        }
    }
}
