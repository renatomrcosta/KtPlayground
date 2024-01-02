package recurse_gen

import kotlin.random.Random

private val random = Random(System.currentTimeMillis())

fun main() {
    generateNumber()
}

// Simple generation that can lead to stack overflow
private tailrec fun generateNumber(): Int {
    println("- Running generation -")
    val result = random.nextInt(0, 1_000_000)
    return if (result >= 1_000_000) {
        println("Done")
        result
    } else {
        generateNumber()
    }
}
