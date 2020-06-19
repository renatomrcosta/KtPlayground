package coroutines.flow

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import util.withExecutionTime
private fun getThreadName() = Thread.currentThread().name

fun main() {
    withExecutionTime {
        runBlocking {
            (1..5).asFlow()
                .filter {
                    println("[${getThreadName()}] filtering $it")
                    it % 2 == 0
                }
                .map {
                    println("[${getThreadName()}] mapping integer $it to string")
                    it.toString()
                }
                .collect {
                    println("[${getThreadName()}] Collecting $it")
                }
        }
    }
}
