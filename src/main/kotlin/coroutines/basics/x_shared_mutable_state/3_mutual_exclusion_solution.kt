package coroutines.basics.x_shared_mutable_state

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/*

CONTEXT: Suppose Jimdo has a campaign that if a Jimdo Website refers a Jimdo partner, via a link, they will get paid a small amount of money per successful referral.

 So. If website A points a link towards a Jimdo partner, and its visitors click on that referral 4000 times, they will get paid 10 cents per referral, making it 40000 cents in this example
 */

private const val VALUE_PER_WEBSITE_VISIT = 10
private const val WEBSITE_VISIT_QUANTITY = 4000

private var totalCampaignAmount = 0
private val totalCampaignAmountMutex = Mutex()

private suspend fun handleVisits(
    amountOfVisits: Int,
    block: suspend (Int) -> Unit
) = coroutineScope {
    repeat(amountOfVisits) {
        launch {
            block(VALUE_PER_WEBSITE_VISIT)
        }
    }
}

fun main() {
    runBlocking(context = Dispatchers.Default) {
        handleVisits(WEBSITE_VISIT_QUANTITY) { visitPayout ->
            totalCampaignAmountMutex.withLock {
                totalCampaignAmount += visitPayout
            }
        }
    }
    println(totalCampaignAmount)
}
