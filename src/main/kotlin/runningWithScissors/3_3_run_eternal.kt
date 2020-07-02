package runningWithScissors

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace
import util.withExecutionTime

fun main() = withExecutionTime {
    try {
        runBlocking(Dispatchers.Default) {
            repeat(10) { workId ->
                launch {
                    SuspendingEternalWorker {
                        // trace("Doing my work with id $workId")
                        delay(1_000)
                        // trace("Ended my work with id $workId")
                    }.run()
                }

            }
            trace("Repeat block is over")
            delay(5_100)
            trace("Cancelling all running jobs")
            this.cancel("Job is done!")
        }
    } catch (e: CancellationException) {
        trace("Possible Cancellation errors can be handled here! ${e.message.toString()}")
    } finally {
        trace("Everything cancelled and cleaned up!")
    }
    trace("I'm done and out of the coroutine scope")
}
