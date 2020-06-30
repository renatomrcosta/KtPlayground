package runningWithScissors

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking {
        repeat(10) {
            launch {
                val worker = BlockingMeasurementWorker {
                    trace("work block start")
                    Thread.sleep(1000)
                    trace("work block finish")
                }
                worker.run()
            }
        }
    }
}
