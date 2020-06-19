package oldcop.i_thread_comparison

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

suspend fun mySuspendFunction() {
    println("Entering suspend function")
    delay(100) // This is a suspended call
    println("Exiting suspend function")
}

fun myBlockingFunction() {
    println("I run some piece of blocking code")
    Thread.sleep(200)
    println("I block some other piece of code")
}

fun main() {
    // withExecutionTime {
    //     println("Normal piece of blocking code")
    //     myBlockingFunction()
    // }

    withExecutionTime {
        runBlocking(Dispatchers.IO) {
            mySuspendFunction()
            println("Jobs starting")
            launch {
                coroutineScope {
                    (1..10).forEach {
                        launch {
                            mySuspendFunction()
                        }
                    }
                }
            }
            println("Jobs have started")
        }
        println("Run Blocking ended")
    }
}


