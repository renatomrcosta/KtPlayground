package runningWithScissors

import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    val worker = BlockingWorker {
        trace("work block start")
        Thread.sleep(1000)
        trace("work block finish")
    }
    worker.run()
}
