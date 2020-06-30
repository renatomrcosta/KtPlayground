package util

import kotlin.system.measureTimeMillis

inline fun withExecutionTime(block: () -> Unit) = measureTimeMillis {
    block()
}.run { println("Total Execution time: ${this}ms") }

fun trace(msg: String) = println("[${getThreadName()}] $msg")

fun getThreadName() = Thread.currentThread().name
