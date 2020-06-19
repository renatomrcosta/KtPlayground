package coroutines.basics.vi_java_interop

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.future
import kotlinx.coroutines.runBlocking
import util.withExecutionTime
import java.util.concurrent.CompletableFuture

private suspend fun fetchUsageInformation(websiteId: String): CompletableFuture<String> = coroutineScope {
    future {
        delay(100)
        "Imagine some meaninful content we derive based on a website"
    }
}

private suspend fun fetchCurrentWebsiteVersion(websiteId: String): CompletableFuture<Int> = coroutineScope {
    future {
        delay(100)
        30
    }
}

private suspend fun compileWebsiteUsageReport(usageInformation: String, websiteVersion: Int): CompletableFuture<Unit> =
    coroutineScope {
        future {
            delay(100)
        }
    }

fun main() = withExecutionTime {
    runBlocking {
        val websiteId = "d5e56c55-a2d9-4672-ab3a-f52dd268ce09" // Arbitrary websiteID
        val usageInformationFuture = fetchUsageInformation(websiteId = websiteId)
        val latestWebsiteVersionFuture = fetchCurrentWebsiteVersion(websiteId = websiteId)

        CompletableFuture.allOf(
            usageInformationFuture,
            latestWebsiteVersionFuture
        ).join()

        compileWebsiteUsageReport(
            websiteVersion = latestWebsiteVersionFuture.join(),
            usageInformation = usageInformationFuture.join()
        ).whenComplete { u, t ->
            println("compiling website usage report complete")
        }.join()

        println("Finished calling fetchUsageInformation")
    }
}
