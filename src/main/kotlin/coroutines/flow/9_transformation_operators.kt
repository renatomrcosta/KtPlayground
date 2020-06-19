package coroutines.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

private suspend fun mySuspendRequest(request: Int): String {
    delay(1000)
    return "request with id $request complete"
}

fun main() {
    withExecutionTime {
        runBlocking {
            listOf(1, 2, 3, 4, 5).asFlow()
                .transform { requestNumber ->
                    emit("Prepping to call request $requestNumber")
                    emit(mySuspendRequest(requestNumber))
                }
                .collect {
                    println(it)
                }
        }
    }
}
