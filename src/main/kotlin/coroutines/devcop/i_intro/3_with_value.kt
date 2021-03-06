package coroutines.devcop.i_intro

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import util.client.singletonHttpWorkClient
import util.trace
import util.withExecutionTime

private suspend fun getValueFromAnotherService(id: Int): Int = withContext(Dispatchers.IO) {
    singletonHttpWorkClient.getValue()
}

fun main() = withExecutionTime {
    runBlocking(Dispatchers.Default) {
        val deferredList = List(1_000) {
            async {
                trace("getting value for id $it")
                getValueFromAnotherService(it)
            }
        }

        deferredList
            .awaitAll()
            .forEach { value ->
                trace("Result value: $value")
            }
    }
}


