package oldcop.ii_composition

import util.withExecutionTime
import java.util.concurrent.CompletableFuture

//Given a websiteId, get me a blob of data containing the website's usage trends, the latest websiteVersion, and compile me a report

private fun fetchUsageInformation(websiteId: String): CompletableFuture<String> =
    CompletableFuture.supplyAsync {
        Thread.sleep(100)
        "Imagine some meaninful content we derive based on a website"
    }

private fun fetchCurrentWebsiteVersion(websiteId: String): CompletableFuture<Int> =
    CompletableFuture.supplyAsync {
        Thread.sleep(100)
        30
    }

private fun compileWebsiteUsageReport(usageInformation: String, websiteVersion: Int): CompletableFuture<Unit> =
    CompletableFuture.supplyAsync {
        Thread.sleep(100)
        if (websiteVersion == 30) {
            throw Exception("I don't like this particular website version")
        }
    }

fun main() = withExecutionTime {
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
    ).exceptionally { exception ->
        println("I have to log the exception")
        println(exception.message)
    }.whenComplete { u, t ->
        println("compiling website usage report complete")
    }.join()


    println("Finished calling fetchUsageInformation")
}
