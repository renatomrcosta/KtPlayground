package runningWithScissors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    runBlocking(Dispatchers.Default) {
        repeat(100) {
            launch {
                val worker = SuspendingWorker {
                    trace("work block start")
                    delay(1000)
                    trace("work block finish")
                }
                worker.run()
            }
        }
    }
}
