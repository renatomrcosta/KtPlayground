package doublecontext

class Usecase3 {
    fun invoke() {
        val dp = DataProvider(name = "something")

        with(MDCAdapter, dp, CacheContext) {
            doWork()
        }

        doIt(dp, MDCAdapter) {
            doWorky()
        }

//        doIt(MDCAdapter, dp, CacheContext) {
//            doWork()
//        }
    }

    context(IDataProvider, MDCAdapter)
    private fun doWorky() {
        println("Work Done")
    }

    context(IDataProvider, MDCAdapter, CacheContext)
    private fun doWork() {
        println("Work Done")
    }
}

fun main() {
    val asd = Usecase3()
    asd.invoke()
}

