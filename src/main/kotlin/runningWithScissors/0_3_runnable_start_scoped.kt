package runningWithScissors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    val worker = BlockingMeasurementWorker {
        trace("work block start")
        runBlocking(Dispatchers.Default) {
            repeat(100) {
                launch {
                    trace("coroutine block starting")
                    Thread.sleep(10_000)
                    trace("coroutine block finishing")
                }
            }
        }
        trace("work block finish")
    }
    worker.run()
}
