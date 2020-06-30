package runningWithScissors

import kotlinx.coroutines.coroutineScope
import util.trace

abstract class Worker : Runnable

class BlockingMeasurementWorker(
    private val fn: () -> Unit
) : Worker() {
    override fun run() {
        trace("Starting Blocking Worker Run")

        fn()

        trace("Finishing Blocking Worker Run")
    }
}

class SuspendingMeasurementWorker(
    private val fn: suspend () -> Unit
) {
    suspend fun run() = coroutineScope {
        trace("Starting Blocking Worker Run")
        fn()
        trace("Finishing Blocking Worker Run")
    }
}
