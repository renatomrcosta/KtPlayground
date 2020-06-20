package kc2019.rxFlowWorkshop.fizz.delay

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.random.Random

val items = Array(20) { "" }

suspend fun dispatchAction(action: suspend () -> Unit) = coroutineScope {
    launch {
        action()
    }
}

fun main() {
    runBlocking {
        withContext(Dispatchers.Default) {
            dispatchAction {
                for (i in 0 until 20) {
                    if (items[i] == "") {
                        delayByRandomMili()
                        items[i] = i.toString()
                    }
                }
            }
            dispatchAction {
                for (i in 0 until 20) {
                    if (i % 3 == 0) {
                        if (items[i] == "Buzz") {
                            delayByRandomMili()
                            items[i] = "FizzBuzz"
                        } else {
                            delayByRandomMili()
                            items[i] = "Fizz"
                        }
                    }
                }
            }
            dispatchAction {
                for (i in 0 until 20) {
                    if (i % 5 == 0) {
                        if (items[i] == "Fizz") {
                            delayByRandomMili()
                            items[i] = "FizzBuzz"
                        } else {
                            delayByRandomMili()
                            items[i] = "Buzz"
                        }
                    }
                }
            }
        }
    }

    items.forEach { println(it) }
}

fun delayByRandomMili() {
    runBlocking {
        delay(Random.nextLong(0L, 20L))
    }
}
