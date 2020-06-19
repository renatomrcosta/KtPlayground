package oldcop.iv_context

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() {
    withExecutionTime {
        runBlocking {
            coroutineScope {
                List(10) {
                    launch {
                        delay(500)
                        println("Executed Job #$it")
                    }
                }
                println("Called list initialization")
            }
            println("Stepped out of coroutineScope block")
        }
    }
}
