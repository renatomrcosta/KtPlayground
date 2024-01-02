package dataprovider

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val channel = Channel<Int>()


fun main() {
    runBlocking {
        launch {
            while(true) {
                val aaa = channel.receive()
                println("received $aaa")
            }
        }

        launch {
            repeat(3) {
                delay(100)
                println("Sending stuff:")
                channel.send(123)
            }
        }
    }
}