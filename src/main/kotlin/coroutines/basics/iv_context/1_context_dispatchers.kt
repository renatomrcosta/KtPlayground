package coroutines.basics.iv_context

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

fun main() = runBlocking<Unit> {
    launch {
        println("No context defined. Uses parent context: Thread Name: ${getThreadName()}")
    }
    launch(Dispatchers.Main) {
        println("Main thread: Thread Name: ${getThreadName()}")
    }
    launch(Dispatchers.Default) {
        println("Default Coroutine: Thread Name: ${getThreadName()}")
    }
    launch(Dispatchers.IO) {
        println("IO Dispatcher: Thread Name: ${getThreadName()}")
    }
    launch(Executors.newSingleThreadExecutor().asCoroutineDispatcher()) {
        println("New thread Context: Thread Name: ${getThreadName()}")
    }
}

private fun getThreadName(): String = Thread.currentThread().name
