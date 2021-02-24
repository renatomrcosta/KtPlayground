package coroutines.basics.ii_composition

import util.withExecutionTime

//Given a websiteId, get me a blob of data containing the website's usage trends, the latest websiteVersion, and compile me a report

private fun fetchUsageInformation(websiteId: String): String {
    Thread.sleep(100)
    return "Imagine some meaningful content we derive based on a website"
}

private fun fetchCurrentWebsiteVersion(websiteId: String): Int {
    Thread.sleep(100)
    return 30
}

private fun compileWebsiteUsageReport(usageInformation: String, websiteVersion: Int) {
    Thread.sleep(100)
}

fun main() = withExecutionTime { // Shorthand to print the time
    val websiteId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09" // Arbitrary websiteID
    val usageInformation = fetchUsageInformation(websiteId = websiteId)
    val latestWebsiteVersion = fetchCurrentWebsiteVersion(websiteId = websiteId)
    compileWebsiteUsageReport(websiteVersion = latestWebsiteVersion, usageInformation = usageInformation)
    println("compiling website usage report complete")
}
