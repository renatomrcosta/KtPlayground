package kc2019.rxFlowWorkshop.fizz.mutex

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

val itemsMutex = Mutex()
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
                        itemsMutex.withLock {
                            items[i] = i.toString()
                        }
                    }
                }
            }
            dispatchAction {
                for (i in 0 until 20) {
                    if (i % 3 == 0) {
                        if (items[i] == "Buzz") {
                            itemsMutex.withLock {
                                items[i] = "FizzBuzz"
                            }
                        } else {
                            itemsMutex.withLock {
                                items[i] = "Fizz"
                            }
                        }
                    }
                }
            }
            dispatchAction {
                for (i in 0 until 20) {
                    if (i % 5 == 0) {
                        if (items[i] == "Fizz") {
                            itemsMutex.withLock {
                                items[i] = "FizzBuzz"
                            }
                        } else {
                            itemsMutex.withLock {
                                items[i] = "Buzz"
                            }
                        }
                    }
                }
            }
        }
    }

    items.forEach { println(it) }
}
