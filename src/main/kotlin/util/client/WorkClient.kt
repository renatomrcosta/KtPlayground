package util.client

import util.withDelay
import kotlin.random.Random
import kotlin.random.nextInt

object WorkClient {
    private val random = Random(System.currentTimeMillis())
    private val numberRange = (0..10_000)

    suspend fun doRemoteWork(millis: Long): Unit =
        withDelay(millis) {}

    suspend fun doRemoteCounting(millis: Long): Int =
        withDelay(millis) { random.nextInt(numberRange) }

    suspend fun getValue(): Int = withDelay { random.nextInt(numberRange) }

}

