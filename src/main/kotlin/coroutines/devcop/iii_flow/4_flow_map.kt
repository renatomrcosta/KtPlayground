package coroutines.devcop.iii_flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

private data class MyWeirdDTO(val amount: Long) {}

private suspend fun Int.toMyWeirdDTO(): MyWeirdDTO {
    return MyWeirdDTO(amount = this.toLong())
}

fun main() {
    withExecutionTime {
        runBlocking(Dispatchers.Default) {
            trace("NORMAL LIST")
            (1..5)
                .toList()
                .map {
                    trace("mapping value $it")
                    delay(1000)
                    it.toMyWeirdDTO()
                }
                .map {
                    trace("mapping value somehow again $it")
                    delay(1000)
                    it
                }.forEach { trace(it) }
        }
    }
    trace("------")

    withExecutionTime {
        runBlocking(Dispatchers.Default) {
            trace("FLOWS GO HERE NOW")
            (1..5)
                .asFlow()
                .buffer()
                .map {
                    trace("mapping flow value $it")
                    delay(1000)
                    it.toMyWeirdDTO()
                }
                .buffer()
                .map {
                    trace("mapping flow somehow again value $it")
                    delay(1000)
                    it
                }.collect {
                    trace(it)
                }
        }
    }
}
