package kc2019.rxFlowWorkshop.rxjava

import io.reactivex.Observable

fun main() {
    // Create an Observable that emits three Stooge strings.
    val stoogesObservable: Observable<String> = Observable.just(
        "Larry",
        "Moe",
        "Curly"
    )

    // Subscribe to the Stooge Observable and print each of its emissions to the console.
    stoogesObservable.subscribe { stooge ->
        println(stooge)
    }
}
