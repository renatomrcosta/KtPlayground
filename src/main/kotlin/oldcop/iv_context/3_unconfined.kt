package oldcop.iv_context

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) {
        println("Unconfined coroutine: Thread Name: ${getThreadName()}")
        delay(500)
        println("Unconfined coroutine: after delay thread name: ${getThreadName()}")
    }
}

private fun getThreadName(): String = Thread.currentThread().name
