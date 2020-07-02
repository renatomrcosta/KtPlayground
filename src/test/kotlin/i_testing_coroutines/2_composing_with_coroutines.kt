package i_testing_coroutines

import coroutines.basics.iii_concurrency.calculateBananasForAReallyLongTime
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withTimeout

class ComposingWithCoroutinesTest : DescribeSpec() {
    init {
        describe("Suspending function test scenario") {
            it("should time-out!") {
                shouldThrow<TimeoutCancellationException> {
                    withTimeout(1000) {
                        calculateBananasForAReallyLongTime()
                    }
                }
            }
        }

        describe("Suspending function test composition scenario") {
            coroutineScope {
                (1..10).asFlow().map {
                    val timeout = it * 500L
                    it("should time-out in ${timeout}ms!") {
                        shouldThrow<TimeoutCancellationException> {
                            withTimeout(timeout) {
                                calculateBananasForAReallyLongTime()
                            }
                        }
                    }
                }
            }
        }
    }
}
