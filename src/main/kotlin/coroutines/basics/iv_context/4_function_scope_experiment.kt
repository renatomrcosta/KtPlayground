package coroutines.basics.iv_context

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    launchInScope("run blocking")
    launchSimple("run blocking")
    launch {
        println("[${getThreadName()}] No context defined. Uses parent context")
        launchInScope("no context defined")
        launchSimple("no context defined")


    }
    launch(Dispatchers.Default) {
        println("[${getThreadName()}] Default Coroutine")
        launchInScope("default")
        launchSimple("default")
    }
    launch(newSingleThreadContext("Banana ThreadContext")) {
        println("[${getThreadName()}] New thread Context")
        launchInScope("new thread")
        launchSimple("new thread")
    }
}

private suspend fun launchInScope(msg: String) = coroutineScope {
    delay(100)
    println("[${getThreadName()}] I'm in the launchInScope function! [$msg]")
}

private suspend fun launchSimple(msg: String) {
    delay(100)
    println("[${getThreadName()}] I'm in the launchSimple function! [$msg]")
}

private fun getThreadName(): String = Thread.currentThread().name
