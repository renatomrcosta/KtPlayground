package oldcop.iv_context

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launch {
        println("No context defined. Uses parent context: Thread Name: ${getThreadName()}")
    }
    launch(Dispatchers.Unconfined) { // This shouldn't be used
        println("Unconfined coroutine: Thread Name: ${getThreadName()}")
    }
    launch(Dispatchers.Default) {
        println("Default Coroutine: Thread Name: ${getThreadName()}")
    }
    launch(Dispatchers.IO) {
        println("IO Dispatcher: Thread Name: ${getThreadName()}")
    }
    launch(newSingleThreadContext("Banana ThreadContext")) {
        println("New thread Context: Thread Name: ${getThreadName()}")
    }
}

private fun getThreadName(): String = Thread.currentThread().name
