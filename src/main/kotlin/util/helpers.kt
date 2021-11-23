package util

import kotlinx.coroutines.time.delay
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.toJavaDuration

inline fun withExecutionTime(block: () -> Unit) = measureTimeMillis {
    block()
}.run { println("Total Execution time: ${this}ms") }

fun trace(msg: Any) = println("[${getThreadName()}] $msg")

fun getThreadName() = Thread.currentThread().name

suspend fun <T> withDelay(timeoutMillis: Long = 100L, block: () -> T): T {
    delay(timeoutMillis.milliseconds.toJavaDuration())
    return block()
}
