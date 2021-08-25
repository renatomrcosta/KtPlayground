package dojos.ii_sealed_classes


sealed class OperationResult(val number: Int) {
    class Success(val value: String) : OperationResult(0)
    class Failure(val exception: Exception) : OperationResult(1)
    object Banana : OperationResult(45)
    class Rain : OperationResult(123)

}

sealed interface Banana {
    enum class Seeds : Banana {
        ASB
    }
}

fun operation(): OperationResult {
    return OperationResult.Success(value = "123")
}

fun main() {
    val result = operation()

    when (result) {
        OperationResult.Banana -> TODO()
        is OperationResult.Success -> TODO()
        is OperationResult.Rain, is OperationResult.Failure -> TODO()
    }

}
