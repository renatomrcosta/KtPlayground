import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.DeleteMessageRequest
import com.amazonaws.services.sqs.model.Message
import com.amazonaws.services.sqs.model.ReceiveMessageRequest
import com.amazonaws.services.sqs.model.SendMessageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.trace

private class SQSManager(
    private val client: AmazonSQS
) {
    companion object {
        private val QUEUE_URL = "https://sqs.eu-central-1.amazonaws.com/257317857496/sample-queue"
        private val DEAD_LETTER_QUEUE_URL = "https://sqs.eu-central-1.amazonaws.com/257317857496/sample-queue"
    }

    private val receiveRequest = ReceiveMessageRequest()
        .withQueueUrl(QUEUE_URL)
        .withMaxNumberOfMessages(8)
        .withWaitTimeSeconds(1)

    suspend fun fetchMessages(): List<Message> = withContext(Dispatchers.IO) {
        val result = client.receiveMessage(receiveRequest)
        //Can treat error
        if (result.sdkHttpMetadata.httpStatusCode != 200) {
            trace("error")
            emptyList()
        } else {
            trace("Got ${result.messages.size} messages")
            result.messages
        }
    }

    suspend fun removeMessage(message: Message) = withContext(Dispatchers.IO) {
        val deleteRequest = DeleteMessageRequest()
            .withQueueUrl(QUEUE_URL)
            .withReceiptHandle(message.receiptHandle)

        client.deleteMessage(deleteRequest)
        trace("Removed message $message from queue")
    }

    suspend fun pushDLQMessage(message: Message) = withContext(Dispatchers.IO) {
        val request = SendMessageRequest()
            .withQueueUrl(DEAD_LETTER_QUEUE_URL)
            .withMessageBody(message.body)

        client.sendMessage(request)
    }
}

private class TransformationManager<T, U> {
    fun transform(value: T): String {
        trace("Transforming value $value")
        return value.toString()
    }
}

private class ProcessingManager<T> {
    suspend fun process(value: T) {
        trace("Processed value $value")
    }
}

private class QueueManager(
    private val sqsManager: SQSManager,
    private val transaformationManager: TransformationManager<Message, String>,
    private val processingManager: ProcessingManager<String>
) {
    val messageChannel = Channel<Message>()
    val errorChannel = Channel<Message>()
    val deleteChannel = Channel<Message>()
    val collectionChannel = Channel<String>()

    suspend fun start() = coroutineScope {
        // Message Handling Block
        launch {
            while (isActive) {
                val messages = sqsManager.fetchMessages()
                messages
                    .asFlow()
                    .buffer()
                    .onEach { message ->
                        messageChannel.send(message)
                    }
                    .buffer()
                    .onEach { message ->
                        deleteChannel.send(message)
                    }
            }
        }

        //Launch error handling (DLQ) block
        launch {
            for (message in errorChannel) {
                sqsManager.pushDLQMessage(message)
            }
        }

        //Message Transformation Block
        launch {
            for (message in messageChannel) {
                val transformed = transaformationManager.toString()
                collectionChannel.send(transformed)
            }
        }

        //launch Processing block
        launch {
            for (item in collectionChannel) {
                processingManager.process(item)
            }
        }
    }
}
