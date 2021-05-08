package util.client

import kotlinx.coroutines.delay

val singletonHttpWorkClient = WorkClient()

class WorkClient {

    suspend fun doRemoteWork(millis: Long): Unit =
        delay(millis)

    suspend fun doRemoteCounting(millis: Long): Int =
        delay(millis).run { 0 }

    suspend fun getValue(): Int = 0
}
