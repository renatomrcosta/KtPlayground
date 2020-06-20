package kc2019.rxFlowWorkshop.rxjava

import io.reactivex.Observable

fun main() {
    // Create an Observable that emits three Stooge strings.
    val stoogesObservable: Observable<String> = Observable.just(
        "Larry",
        "Moe",
        "Curly"
    )

    // Subscribe to the Stooge Observable and print each of their fifth letters to the console.
    // Handle any out of bounds exceptions.
    // Print out a message on completion.
    stoogesObservable
        .map { stoogeName -> stoogeName[4] }
        .subscribe({ letter ->
            println(letter)
        }, { throwable ->
            println(throwable)
        }, {
            println("All done!")
        })
}
