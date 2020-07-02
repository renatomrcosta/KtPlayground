package runningWithScissors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking(Dispatchers.IO) {
        repeat(100) {
            launch {
                val worker = SuspendingWorker {
                    trace("work block start")
                    Thread.sleep(1000)
                    trace("work block finish")
                }
                worker.run()
            }
        }
    }
}
