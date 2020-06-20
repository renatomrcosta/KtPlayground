package kc2019.rxFlowWorkshop.rxjava

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

private const val FREEZING_POINT_KELVIN = 273.15

private fun Observable<Int>.filterIfBelowFreezing(): Observable<Int> =
    filter { temperature -> temperature <= FREEZING_POINT_KELVIN }

fun main() {
    // Create an Observable from the numbers 1 through 1000.
    // Call filterIfBelowFreezing on your Observable on Schedulers.io.
    // Subscribe to your Observable and print each emission to the console on Schedulers.computation.
    //
    // (Schedulers.computation is not a direct analogue to Dispatchers.Default, but they have
    // their similarities.)
    Observable.fromIterable(1..1000)
        .filterIfBelowFreezing()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .subscribe { temperature ->
            println(temperature)
        }
}
