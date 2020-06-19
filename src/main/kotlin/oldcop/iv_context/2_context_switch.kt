package oldcop.iv_context

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    newSingleThreadContext("Banana ThreadContext").use { context1 ->
        newSingleThreadContext("Banana ThreadContext 2").use { context2 ->
            runBlocking(context1) {
                println("Running in Context 1: Thread Name: ${getThreadName()}")
                withContext(context2) {
                    println("Running in Context 2: Thread Name: ${getThreadName()}")
                }
                println("Running in Context 1: Thread Name: ${getThreadName()}")
            }
        }
    }
}

private fun getThreadName(): String = Thread.currentThread().name
