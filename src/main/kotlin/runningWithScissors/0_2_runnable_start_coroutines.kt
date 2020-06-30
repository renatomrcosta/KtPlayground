package runningWithScissors

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    val worker = BlockingWorker {
        trace("work block start")
        runBlocking {
            repeat(5) {
                launch {
                    trace("coroutine block starting")
                    Thread.sleep(1000)
                    trace("coroutine block finishing")
                }
            }
        }
        trace("work block finish")
    }
    worker.run()
}
