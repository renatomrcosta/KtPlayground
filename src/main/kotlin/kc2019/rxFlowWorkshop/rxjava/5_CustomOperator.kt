package kc2019.rxFlowWorkshop.rxjava

import io.reactivex.Observable

private const val FREEZING_POINT_KELVIN = 273.15

// Add an extension function to Observable<Int> that filters out numbers below the freezing point
//  of water in Kelvin.
private fun Observable<Int>.filterIfBelowFreezing(): Observable<Int> =
    filter { temperature -> temperature <= FREEZING_POINT_KELVIN }

fun main() {
    // Create an Observable from the numbers 1 through 1000.
    // Apply your new extension function to the flow you created.
    // Collect the integers emitted and print them to the console.
    Observable.fromIterable(1..1000)
        .filterIfBelowFreezing()
        .subscribe { temperature ->
            println(temperature)
        }
}
