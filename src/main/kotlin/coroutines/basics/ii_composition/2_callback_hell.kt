package coroutines.basics.ii_composition

import util.withExecutionTime

//Given a websiteId, get me a blob of data containing the website's usage trends, the latest websiteVersion, and compile me a report

private fun <T> fetchUsageInformation(websiteId: String, onComplete: (String) -> T) {
    Thread.sleep(100)
    onComplete("Imagine some meaningful content we derive based on a website")
}

private fun <T> fetchCurrentWebsiteVersion(websiteId: String, onComplete: (Int) -> T) {
    Thread.sleep(100)
    onComplete(30)
}

private fun compileWebsiteUsageReport(usageInformation: String, websiteVersion: Int, onComplete: () -> Unit) {
    Thread.sleep(100)
    onComplete()
}

fun main() = withExecutionTime {
    val websiteId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09" // Arbitrary websiteID
    fetchUsageInformation(websiteId = websiteId) { usageInformation ->
        fetchCurrentWebsiteVersion(websiteId = websiteId) { latestWebsiteVersion ->
            compileWebsiteUsageReport(websiteVersion = latestWebsiteVersion, usageInformation = usageInformation) {
                println("compiling website usage report complete")
            }
        }
    }
    println("Hey, I'm out of the callbacks!")
}
