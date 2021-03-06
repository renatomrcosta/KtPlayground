package coroutines.basics.x_shared_mutable_state

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

/*

CONTEXT: Suppose Jimdo has a campaign that if a Jimdo Website refers a Jimdo partner, via a link, they will get paid a small amount of money per successful referral.

 So. If website A points a link towards a Jimdo partner, and its visitors click on that referral 4000 times, they will get paid 10 cents per referral, making it 40000 cents in this example
 */

private const val VALUE_PER_WEBSITE_VISIT = 10
private const val WEBSITE_VISIT_QUANTITY = 4000

private var totalCampaignAmount: AtomicInteger = AtomicInteger(0)

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
            totalCampaignAmount.addAndGet(visitPayout)
        }
    }
    println(totalCampaignAmount)
}
