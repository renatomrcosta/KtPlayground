package coroutines.devcop.x_sharedState

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
CONTEXT: Suppose Jimdo has a campaign that if a Jimdo Website refers a Jimdo partner via a link,
they will get paid a small amount of money per successful referral.

ASSUMPTION: Every referral click generates an idempotency key. We then check to see if each click was
valid and unique.

CONCLUSION: So. If website A points a link towards a Jimdo partner,
and its visitors click on that referral 4000 times, they will get paid 10 cents per referral,
making it 40000 cents in this example
 */

private const val VALUE_PER_WEBSITE_VISIT_IN_CENTS = 10
private const val WEBSITE_VISIT_QUANTITY = 4000

private suspend fun isClickValid(): Boolean {
    delay(150)
    return true
}

fun main() {
    var totalCampaignAmount = 0
    runBlocking(context = Dispatchers.Default) {
        repeat(WEBSITE_VISIT_QUANTITY) {
            launch {
                if (isClickValid()) {
                    totalCampaignAmount += VALUE_PER_WEBSITE_VISIT_IN_CENTS
                }
            }
        }
    }
    println(totalCampaignAmount)
}
