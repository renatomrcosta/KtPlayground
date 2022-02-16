package coroutines.basics.iv_context

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import util.trace
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

suspend fun main() = coroutineScope {
    trace("Launching Some Coroutines with a context value")

    repeat(10) {
        launch(Dispatchers.Default + CustomCoroutineContextElement("Some Value $it")) {
            trace("Coroutine Launching some children")

            repeat(10) { c ->
                launch {
                    val contextValue = this.coroutineContext[CustomCoroutineContextElement]?.bananinha
                    trace("Child ${it}$c | Value in the context is: $contextValue")
                }
            }

        }
    }
}

private class CustomCoroutineContextElement(
    val bananinha: String
) : AbstractCoroutineContextElement(CustomCoroutineContextElement) {
    companion object Key : CoroutineContext.Key<CustomCoroutineContextElement>
}