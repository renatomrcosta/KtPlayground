package coroutines.basics.ii_composition

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

private suspend fun fetchUsageInformation(websiteId: String): String = coroutineScope {
    delay(100)
    "Imagine some meaninful content we derive based on a website"
}

private suspend fun fetchCurrentWebsiteVersion(websiteId: String): Int = coroutineScope {
    delay(100)
    30
}

private suspend fun compileWebsiteUsageReport(usageInformation: String, websiteVersion: Int) = coroutineScope {
    delay(100)
}

fun main() = withExecutionTime {
    runBlocking(context = Dispatchers.IO) {
        val websiteId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09" // Arbitrary websiteID
        val usageInformation = fetchUsageInformation(websiteId = websiteId)
        val latestWebsiteVersion = fetchCurrentWebsiteVersion(websiteId = websiteId)

        compileWebsiteUsageReport(
            websiteVersion = latestWebsiteVersion,
            usageInformation = usageInformation
        )
    }
    println("compiling website usage report complete")
}
