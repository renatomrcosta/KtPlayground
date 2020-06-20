package kc2019.rxFlowWorkshop.fizz.initial

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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
                        items[i] = i.toString()
                    }
                }
            }
            dispatchAction {
                for (i in 0 until 20) {
                    if (i % 3 == 0) {
                        if (items[i] == "Buzz") {
                            items[i] = "FizzBuzz"
                        } else {
                            items[i] = "Fizz"
                        }
                    }
                }
            }
            dispatchAction {
                for (i in 0 until 20) {
                    if (i % 5 == 0) {
                        if (items[i] == "Fizz") {
                            items[i] = "FizzBuzz"
                        } else {
                            items[i] = "Buzz"
                        }
                    }
                }
            }
        }
    }

    items.forEach { println(it) }
}
