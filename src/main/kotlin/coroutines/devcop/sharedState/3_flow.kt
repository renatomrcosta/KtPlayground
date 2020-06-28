package coroutines.devcop.sharedState

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import util.withExecutionTime

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
    println("Checking validity")
    delay(10)
    return true
}

private fun valueEmissionFlow() = flow<Int> {
    repeat(WEBSITE_VISIT_QUANTITY) {
        println("Value Emission $it")
        emit(VALUE_PER_WEBSITE_VISIT_IN_CENTS)
    }
}

fun main() {
    withExecutionTime {
        runBlocking(context = Dispatchers.Default) {
            var total = 0
            val valueFlow = valueEmissionFlow()
                .buffer(100)
                .filter { isClickValid() }
                .collect {
                    total += it
                }
            println("Total: $total")
        }
    }
}

