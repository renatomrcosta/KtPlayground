package kt_1_6.sliceable_dispatchers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.trace
import util.withExecutionTime

@OptIn(ExperimentalCoroutinesApi::class)

// The parallelism parameter can be a lower bound than the 64 the IO gives you, but also can ignore the upper bound if you want to provide a sensible pool to specific workloads:
// Read more: https://github.com/Kotlin/kotlinx.coroutines/issues/2943
suspend fun main() = withContext(Dispatchers.IO.limitedParallelism(parallelism = 100)) {
    withExecutionTime {
        List(100_000) {
            launch {
                delay(1000)
                trace("Executed Job #$it")
            }
        }
    }
}
