package runningWithScissors

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.isActive
import util.trace
import java.util.concurrent.atomic.AtomicInteger

abstract class Worker : Runnable

class BlockingWorker(
    private val fn: () -> Unit
) : Worker() {
    override fun run() {
        trace("Starting Blocking Worker Run")

        fn()

        trace("Finishing Blocking Worker Run")
    }
}

class BlockingEternalWorker(
    private val fn: () -> Unit
) {
    private val executionCounter = AtomicInteger(0)
    fun run() {
        try {
            trace("Starting ETERNAL Worker Run")
            while (true) {
                fn()
                executionCounter.getAndAdd(1)
            }
        } finally {
            trace("Finishing Blocking ETERNAL Worker Run || Executed $executionCounter times")
        }
    }
}

class SuspendingWorker(
    private val fn: suspend () -> Unit
) {
    suspend fun run() = coroutineScope {
        trace("Starting Blocking Worker Run")
        fn()
        trace("Finishing Blocking Worker Run")
    }
}

class SuspendingEternalWorker(
    private val fn: suspend () -> Unit
) {
    private val executionCounter = AtomicInteger(0)
    suspend fun run() = coroutineScope {
        try {
            trace("Starting Eternal Worker Run")
            while (isActive) {
                fn()
                executionCounter.getAndAdd(1)
            }
        } finally {
            trace("Finishing Eternal Worker Run || Executed $executionCounter times")
        }
    }
}
