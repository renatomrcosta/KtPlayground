package coroutines.basics.i_thread_comparison

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import util.trace
import kotlin.concurrent.thread

fun main() {
    runBlocking {
        trace("I'm in the main fun, runblocking")
    }

    runBlocking(Dispatchers.Default) {
        trace("I'm in the main fun, with Context")
    }


    runBlocking(newSingleThreadContext("BananaThread")) {
        trace("I'm in the main fun, with Context")
    }

    thread {
        trace("I'm inside a new thread!")
        runBlocking {
            trace("I'm in the main fun, runblocking")
        }

        runBlocking(newSingleThreadContext("BananaThread2")) {
            trace("I'm in the main fun, with Context")
            runBlocking(Dispatchers.IO) {
                trace("I shouldn't exist")
            }
        }
    }.join()
}
