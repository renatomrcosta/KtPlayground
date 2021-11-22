package kt_1_4_event.ii_coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace
import java.util.UUID
import kotlin.random.Random

private suspend fun startSubscriber(
    flow: SharedFlow<Event>,
    id: String,
): Unit =
    flow
        .collect { event ->
            when (event) {
                is Event.PaidEvent -> trace("[$id] - Received Paid Event $event")
                is Event.SubscribedEvent -> trace("[$id] - Received Subscribed Event $event")
            }
        }

private sealed class Event {
    abstract val eventId: String

    data class SubscribedEvent(override val eventId: String, val subscriptionReason: String) : Event()
    data class PaidEvent(override val eventId: String, val valuePaid: Double) : Event()
}

private class EventBus {
    private val _events = MutableSharedFlow<Event>(replay = 100, extraBufferCapacity = 100)
    val events: SharedFlow<Event> get() = _events

    suspend fun produceEvent(event: Event) = _events.emit(event)
}

private val RNGesus = Random(System.currentTimeMillis())

fun main() = runBlocking<Unit> {
    val eventBus = EventBus()

    launch { startSubscriber(eventBus.events, id = "Main Subscriber") }
    launch { startSubscriber(eventBus.events, id = "2 Subscriber") }
    launch(Dispatchers.Default) { startSubscriber(eventBus.events, id = "Dispatcher Default Subscriber") }

    repeat(20) {
        launch(Dispatchers.IO) {
            eventBus.produceEvent(
                Event.PaidEvent(
                    eventId = UUID.randomUUID().toString(),
                    valuePaid = RNGesus.nextDouble(0.0, Double.MAX_VALUE)
                )
            )
        }
    }

    repeat(20) {
        launch(Dispatchers.IO) {
            eventBus.produceEvent(
                Event.SubscribedEvent(eventId = UUID.randomUUID().toString(), subscriptionReason = "Some reason")
            )
        }
    }
}
