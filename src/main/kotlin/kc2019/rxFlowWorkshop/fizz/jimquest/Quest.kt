package kc2019.rxFlowWorkshop.fizz.jimquest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

/*

CONTEXT: Suppose Jimdo has a campaign that if a Jimdo Website refers a Jimdo partner, via a link, they will get paid a small amount of money per successful referral.

 So. If website A points a link towards a Jimdo partner, and its visitors click on that referral 4000 times, they will get paid 10 cents per referral, making it 40000 cents in this example

 Steps:
  1. Explain the context to the candidate, and have him explain the implementation.
    a. Points that might come up during discussion:
        What are lambdas? What are coroutines? what are its scopes? What is runBlocking? what is launch?
        These things can be approached and discussed with the candidate to gauge his/her understanding of software design and intricacies even if they do not have Kotlin experience.
    2. Discuss what sort of issues can occur in this software
        a. We expect them to get that the result should be 4000 * 10, of course, but
        more importantly we expect them to catch the *race condition*. If you execute this software a bunch of times, and increase the quantity of visits, more often than not the result will be not right. This will open up the discussion of why.
        b. If the candidate is not certain, ask the following:
            "How many operations are written in the row with the following statement:"
                totalCampaignAmount += visitPayout
            They should be able to explain the three operations present: Reading the totalAmount, summing it to visitPayout, and assigning back to totalCampaignAmount. This should make clear where the race condition might come from

    3. Once the problem is clear, then you can discuss this issue of "Shared Mutable State" with them openly: What are the options? What are the approaches?
        a. Thread Scopes -> In Kotlin, you could force them all to run in a single thread, though this is deprecated
        b. Not having asynchronicity -> It may work depending on the problem at hand. Discussing ways to simplify things should always be part of the deal.
        c. Atomic types: Thread safe types for these sorts of operations. Mind that the candidate also should explain why we don't always use them instead (it's costly, in a nutshell)
        d. Mutexes (Mutual Exclusions):
            You can provide access locks to safeguard dangerous operations and ensure they proceed only one at a time.
            In Kotlin you can very easily instantiate a Mutex() object and utilize the withLock block to write simple and readable mutexed operations

 */

const val VALUE_PER_WEBSITE_VISIT = 10
const val WEBSITE_VISIT_QUANTITY = 4000

val totalMutex = Mutex()
var totalCampaignAmount = 0

suspend fun handleVisits(
    amountOfVisits: Int,
    visit: suspend (Int) -> Unit
) = coroutineScope {
    repeat(amountOfVisits) {
        launch {
            visit(VALUE_PER_WEBSITE_VISIT)
        }
    }
}

fun main() {
    runBlocking {
        withContext(Dispatchers.Default) {
            handleVisits(WEBSITE_VISIT_QUANTITY) { visitPayout ->
                totalMutex.withLock {
                    totalCampaignAmount += visitPayout
                }
            }
        }
    }
    println(totalCampaignAmount)
}
