package lazydeferred

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

fun main() =
    withExecutionTime {
        runBlocking<Unit> {
            // val instance1 = MyService()

            // // It should get stuff from the API once, therefore only one log on the console
            // repeat(10) { instance1.getMyCollectionFromTheAPIBlocking().asFlow().collect { println(it) } }

            val coInstance = MyCoService()

            // It should get stuff from the API once, therefore only one log on the console
            repeat(10) {
                launch {
                    coInstance.getMyCollectionFromTheAPISuspend().asFlow().collect { println(it) }
                }
            }

            delay(5_000)
            repeat(10) {
                launch {
                    coInstance.getMyCollectionFromTheAPISuspend().asFlow().collect { println(it) }
                }
            }
        }
    }

abstract class CoLazyAble(
    dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    private val scope = CoroutineScope(dispatcher)

    fun <T> coLazy(
        initializer: suspend CoroutineScope.() -> T
    ) =
        scope.async(start = CoroutineStart.LAZY) {
            initializer()
        }
}

// Coroutine Edition
private class MyCoService : CoLazyAble() {

    private val myUserList = coLazy {
        getFromAPISuspend()
    }

    suspend fun getMyCollectionFromTheAPISuspend(): List<User> = myUserList.await()

    private suspend fun getFromAPISuspend() = coroutineScope {
        println(" Hey, the suspend API was called! You gotta wait 10 seconds for the results to trickle in!")
        delay(10_000)
        listOf(
            User(id = "3f38349c-2092-4542-97b8-4926d416f6ba", "1"),
            User(id = "68e0e931-b76c-4134-ab47-ea2a7dc7d85e", "2"),
            User(id = "336ea309-d397-43a6-895d-913569cbb809", "3"),
        )
    }
}

// Blocking Edition
private class MyService {
    private val myUserListBlocking by lazy {
        getFromAPI()
    }

    fun getMyCollectionFromTheAPIBlocking() = this.myUserListBlocking

    private fun getFromAPI() = listOf(
        User(id = "3f38349c-2092-4542-97b8-4926d416f6ba", "1"),
        User(id = "68e0e931-b76c-4134-ab47-ea2a7dc7d85e", "2"),
        User(id = "336ea309-d397-43a6-895d-913569cbb809", "3"),
    ).also {
        println(" Hey, the API was called!")
    }
}

private data class User(val id: String, val name: String)
