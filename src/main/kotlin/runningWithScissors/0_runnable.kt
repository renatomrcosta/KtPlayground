package runningWithScissors

import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    repeat(10) {
        val worker = BlockingMeasurementWorker {
            trace("work block start")
            Thread.sleep(1000)
            trace("work block finish")
        }
        worker.run()
    }
}
