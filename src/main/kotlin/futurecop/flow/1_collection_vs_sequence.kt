package futurecop.flow

private fun mySequence(): Sequence<Int> = sequence {
    for (i in 1..3) {
        Thread.sleep(100)
        yield(i)
    }
}

fun main() {
    listOf(1,2,3).forEach { println(it) }
    mySequence().forEach{ println(it)}
}
