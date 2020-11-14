package i_testing_coroutines

import coroutines.basics.iii_concurrency.calculateBananasForAReallyLongTime
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class TestingCoroutinesTest : DescribeSpec() {
    init {

        describe("Testing coroutines!") {
            it("should work with 100K jobs") {
                List(100_000) {
                    launch {
                        delay(1_000)
                        println("Job Complete!")
                    }
                }
            }
        }

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
            (1..10).forEach {
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
