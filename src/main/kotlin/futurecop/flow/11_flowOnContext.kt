package futurecop.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

private fun getThreadName() = Thread.currentThread().name

private fun myFlow() = flow<Int> {
    for(i in 1..5) {
        println("[${getThreadName()}] Emitting $i")
        emit(i)
    }
}

fun main() {
    withExecutionTime {
        runBlocking {
            myFlow()
                .flowOn(Dispatchers.Default)
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
