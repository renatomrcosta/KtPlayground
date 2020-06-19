package coroutines.basics.ii_composition

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
    if (websiteVersion == 30) {
        throw Exception("I don't like this particular website version")
    }
}

fun main() = withExecutionTime {
    runBlocking(context = Dispatchers.Default) {
        val websiteId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09" // Arbitrary websiteID
        val usageInformationDeferred = async { fetchUsageInformation(websiteId = websiteId) }
        val latestWebsiteVersionDeferred = async { fetchCurrentWebsiteVersion(websiteId = websiteId) }

        try {
            compileWebsiteUsageReport(
                websiteVersion = latestWebsiteVersionDeferred.await(),
                usageInformation = usageInformationDeferred.await()
            )
        } catch (exception: Exception) {
            println("I have to log the exception")
            println(exception.message)
        } finally {
            println("compiling website usage report complete")
        }
    }
}
