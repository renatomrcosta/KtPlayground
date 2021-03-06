package runningWithScissors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    val worker = BlockingEternalWorker {
        trace("work block start")
        runBlocking(Dispatchers.Default) {
            launch {
                trace("coroutine block starting")
                withContext(Dispatchers.IO) { Thread.sleep(1000) }
                trace("coroutine block finishing")
            }
        }
        trace("work block finish")
    }
    worker.run()
}
