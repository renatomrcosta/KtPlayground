package kc2019.rxFlowWorkshop.fizz.jimquestpractice

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/*
CONTEXT: Suppose Jimdo has a campaign that if a Jimdo Website refers a Jimdo partner,
via a link, they will get paid a small amount of money per successful referral.
 */

const val VALUE_PER_WEBSITE_VISIT = 10
const val WEBSITE_VISIT_QUANTITY = 40_000

var totalCampaignAmount = 0

fun talkToServerForValidation() {
    // It will take a while
}

fun main() {
    runBlocking {
        withContext(Dispatchers.Default) {
            repeat(WEBSITE_VISIT_QUANTITY) {
                launch {
                    talkToServerForValidation() // Long, blocking call
                    totalCampaignAmount += VALUE_PER_WEBSITE_VISIT
                }
            }
        }
    }
    println(totalCampaignAmount)
}
