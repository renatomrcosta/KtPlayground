package kc2019.rxFlowWorkshop.rxjava

import io.reactivex.Single

fun main() {
    // Create a Single that emits an integer.
    val laidBackSingle: Single<Int> = Single.just(311)

    // Subscribe to the Single and print its emission to the console.
    laidBackSingle.subscribe { number ->
        println(number)
    }
}
