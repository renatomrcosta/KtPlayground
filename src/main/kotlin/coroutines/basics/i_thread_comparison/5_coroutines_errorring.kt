package coroutines.basics.i_thread_comparison

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() =
    withExecutionTime {
        runBlocking {
            coroutineScope {
                List(10) {
                    launch {
                        delay(1000)
                        if (it == 5) {
                            throw Exception("Something with coroutine Funky!")
                        }
                        println("Executed Job #$it")
                    }
                }
            }
        }
    }


