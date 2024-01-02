package dataprovider

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.jvmErasure

data class MessageType3(val string: String)

data class DataProvider3(
    val string: String = "",
    val messageType1: Deferred<MessageType3>,
    val messageType3: Deferred<MessageType3>,
    val messageType4: Deferred<MessageType3>,
)

suspend fun <T> DataProvider3.provide(block: suspend (DataProvider3) -> T) {
    try {
        block(this)
    } finally {
        this::class.memberProperties
            .filter { it.returnType.jvmErasure == Deferred::class } // Filter directly through erasure tho
            .forEach { (it.call(this) as? Deferred<*>)?.checkAndCancelMember() } // do a safecast and go from there
    }
}

fun <T, Y : Deferred<T>> Y.checkAndCancelMember() {
    if (!this.isActive) {
        this.cancel()
    }
}

fun provide3(scope: CoroutineScope): DataProvider3 {
    val messageType1 = scope.async(start = CoroutineStart.LAZY) {
        println("Started providing the <T<")
        delay(1000)
        MessageType3("1")
    }

    val messageType3 = scope.async(start = CoroutineStart.LAZY) {
        println("Started providing the <T>")
        delay(1000)
        MessageType3("3")
    }

    val messageType4 = scope.async(start = CoroutineStart.LAZY) {
        println("3333333 <T>")
        delay(1000)
        MessageType3("5")
    }

    return DataProvider3(
        "",
        messageType1,
        messageType3,
        messageType4,
    )
}

class Usecase3 {
    suspend fun doit() = coroutineScope {
        println("yo")
        provide3(scope = this).provide { provider ->
            println("blah")
//            launch {
//                provider.messageType1.await().also { println(it) }
//            }
//            provider.messageType4.await().also { println(it) }
//            provider.messageType3.await().also { println(it) }
            println("over and out")
        }
        println("done")
    }
}

suspend fun main() {
    println("starting")
    Usecase3().doit()
    println("finishing")
}
