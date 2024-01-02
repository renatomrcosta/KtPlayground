package dataprovider

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

data class MessageType(val string: String)

data class DataProvider(
    val messageType1: Deferred<MessageType>,
    val messageType2: Deferred<MessageType>,
)

fun provide(scope: CoroutineScope = CoroutineScope(Dispatchers.IO)): DataProvider {
    val messageType1 = scope.async(start = CoroutineStart.LAZY) {
        println("Started providing the <T<")
        delay(1000)
        MessageType("1")
    }

    val messageType2 = scope.async(start = CoroutineStart.LAZY) {
        println("Started providing the <T>")
        delay(1000)
        MessageType("2")
    }

    return DataProvider(
        messageType1,
        messageType2,
    )
}

class Usecase() {
    suspend fun doit() {
        val provider = provide()
        provider.messageType1.await()
    }
}

suspend fun main() {
    println("starting")
    Usecase().doit()
    println("finishing")
}
