package dojos.ii_sealed_classes

sealed class Result(val reason: String) {
    class Success(val calculation: Int) : Result(reason = "success")
    class Failure : Result(reason = "failure")
    class Explosion : Result(reason = "explosion")

    fun message() = when(this) {
        is Explosion, is Failure -> "Something funky! $reason"
        is Success -> "good, good! $calculation"
    }
}

fun main() {
    val result = Result.Success(calculation = 123)
    println(result.message())
}
