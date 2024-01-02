package doublecontext

class Usecase2 {
    fun invoke() {
        val dp = DataProvider(name = "something")

        withCacheReceiver("some cache") {
            withMDCReceiver("object" to "🍌") {
                dp.useReceiver {
                    doWork()
                }
            }
        }
    }

    context(IDataProvider, MDCAdapter, CacheContext)
    private fun doWork() {
        println("Work Done")
    }
}

fun main() {
    val asd = Usecase2()
    asd.invoke()
}

