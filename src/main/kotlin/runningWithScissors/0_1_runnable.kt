package runningWithScissors

import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    val worker = BlockingWorker {
        trace("work block start")
        Thread.sleep(1000)
        trace("work block finish")
    }
    val worker2 = BlockingWorker {
        trace("worker 2block start")
        Thread.sleep(1000)
        trace("worker 2 block finish")
    }
    worker.run()
    worker2.run()
    worker.run()
}
