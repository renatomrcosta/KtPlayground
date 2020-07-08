package coroutines.devcop.iii_flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

private data class MyWeirdDTO(val amount: Long) {}

private suspend fun Int.toMyWeirdDTO(): MyWeirdDTO {
    delay(1000)
    return MyWeirdDTO(amount = this.toLong())
}

fun main() {
    withExecutionTime {
        runBlocking(Dispatchers.IO) {
            trace("NORMAL LIST")
            (1..5)
                .toList()
                .map {
                    trace("mapping value $it")
                    it.toMyWeirdDTO()
                }.forEach { trace(it) }
        }
    }
    trace("------")

    withExecutionTime {
        runBlocking(Dispatchers.IO) {
            trace("FLOWS GO HERE NOW")
            (1..5)
                .asFlow()
                // .buffer()
                .map {
                    trace("mapping flow value $it")
                    it.toMyWeirdDTO()
                }
                // .buffer()
                .collect {
                    trace(it)
                }
        }
    }
}
