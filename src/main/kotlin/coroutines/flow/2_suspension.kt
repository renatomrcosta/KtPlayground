package coroutines.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private fun myFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Default) {
        for(i in 1..3) {
            println("I'm not blocked and my index is $i")
            delay(100)
        }
    }
    myFlow().collect {
        println(it)
    }
}
