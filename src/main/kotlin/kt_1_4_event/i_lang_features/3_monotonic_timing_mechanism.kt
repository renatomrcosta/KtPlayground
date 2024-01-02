package kt_1_4_event.i_lang_features

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import util.trace
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

private suspend fun slowFunction(): Int {
    delay(2000)
    trace("I'm done with the slow function!")
    return 42
}

@ExperimentalTime
fun main() = runBlocking<Unit> {
    measureTimeSample()
    measureTimedValueSample()
}

@ExperimentalTime
private suspend fun measureTimeSample() {
    val duration = measureTime {
        val result = slowFunction()
        trace("SlowFunction Result = $result")
    }
    trace("Duration was ${duration.inWholeMilliseconds}ms")
}

@ExperimentalTime
private suspend fun measureTimedValueSample() {
    val (result, duration) = measureTimedValue {
        slowFunction()
    }
    trace("SlowFunction Result = $result")
    trace("Duration was ${duration.inWholeMilliseconds}ms")
}
