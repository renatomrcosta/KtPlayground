package kt_1_7_20

import java.time.LocalDate

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    // normal closed range ranges:
    run {
        val inclusive = 1..10
        val exclusive = 1 until 10

//        val doesntwork = 1.0 until 10.0

        println(10 in inclusive)
        println(10 in exclusive)
    }
    run {
        val closedRange = 1..<10
        val openEndedDouble = 1.0..<10.0
        val openEndedDates = LocalDate.of(2022,10,10)..<LocalDate.of(2022,10,20)

        println(closedRange)
        println(openEndedDouble)
        println(openEndedDates)

        println(10 in closedRange)
        println(9.99 in openEndedDouble)
        println(10.0 in openEndedDouble)
        println(LocalDate.now() in openEndedDates)
    }

}
