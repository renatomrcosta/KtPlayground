package doublecontext

class Usecase {

    fun invoke() {
        val dp = DataProvider(name = "something")

        withCache("some cache") {
            withMDC("object" to "🍌") { mdc ->
                dp.use { dataProvider ->
                    println("Something is happening here $dataProvider")
                }
            }
        }
    }
}

fun main() {
    val asd = Usecase()
    asd.invoke()
}

