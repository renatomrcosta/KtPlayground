package util.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get

val singletonHttpWorkClient = WorkClient()

class WorkClient {
    private val httpClient = HttpClient(CIO) {}

    suspend fun doRemoteWork(millis: Int): Unit =
        httpClient.get<Unit>("$SERVICE_URL$WORK_URI?millis=$millis")

    suspend fun doRemoteCounting(millis: Int): Int =
        httpClient.get<Int>("$SERVICE_URL$COUNTER_URI?millis=$millis")

    suspend fun getValue(): Int = httpClient.get<Int>("$SERVICE_URL$VALUE_URI")

    companion object {
        private const val SERVICE_URL = "http://localhost:8080"
        private const val WORK_URI = "/work/wait"
        private const val COUNTER_URI = "/work/count"
        private const val VALUE_URI = "/work/value"
    }
}
