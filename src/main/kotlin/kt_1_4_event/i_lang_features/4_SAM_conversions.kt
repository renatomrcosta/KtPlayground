package kt_1_4_event.i_lang_features

import kotlinx.coroutines.runBlocking
import util.trace

private class MyWorker(private val runnable: Runnable) {
    fun doWork() = runnable.run()
}

fun main() {
    val worker = MyWorker {
        trace("Hey, I'm doing some work out here!")
    }
    runBlocking {
        repeat(10) {
            worker.doWork()
        }
    }
}
