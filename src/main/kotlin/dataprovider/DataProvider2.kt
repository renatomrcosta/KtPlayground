package dataprovider

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

data class MessageType2(val string: String)

data class DataProvider2(
    val messageType1: Deferred<MessageType2>,
    val messageType2: Deferred<MessageType2>,
)

fun <T> withProvider(dataProvider2: DataProvider2, block: DataProvider2.() -> T) {
    block(dataProvider2).also {
        if (!dataProvider2.messageType1.isActive) {
            dataProvider2.messageType1.cancel()
        }
        if (!dataProvider2.messageType2.isActive) {
            dataProvider2.messageType2.cancel()
        }
    }
}

suspend fun provide2(dispatcher: CoroutineDispatcher = Dispatchers.IO): DataProvider2 = withContext(dispatcher) {
    val messageType1 = async(start = CoroutineStart.LAZY) {
        println("Started providing the <T<")
        delay(1000)
        MessageType2("1")
    }

    val messageType2 = async(start = CoroutineStart.LAZY) {
        println("Started providing the <T>")
        delay(1000)
        MessageType2("2")
    }

    DataProvider2(
        messageType1,
        messageType2,
    )
}

class Usecase2 {
    suspend fun doit() {
        val provider = provide2()
        provider.messageType1.await()
        provider.messageType2.await()
    }
}

fun main() = runBlocking {
    println("starting")
    Usecase2().doit()
    println("finishing")
}
