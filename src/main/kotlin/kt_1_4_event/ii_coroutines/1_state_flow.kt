package kt_1_4_event.ii_coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import util.trace

data class DownloadProgress(
    val status: DownloadStatus,
    val percentComplete: Double = 0.0
)

enum class DownloadStatus {
    NOT_REQUESTED,
    IN_PROGRESS,
    FAILED,
    FINISHED
}

@ExperimentalCoroutinesApi
class DownloadModel {
    private val _state = MutableStateFlow(
        INITIAL_STATE
    )
    val state: StateFlow<DownloadProgress> get() = _state

    suspend fun download() {
        if (_state.compareAndSet(INITIAL_STATE, DownloadProgress(status = DownloadStatus.IN_PROGRESS))) {
            initializeConnection()
            transferTheFile()
            _state.value = DownloadProgress(status = DownloadStatus.FINISHED, percentComplete = 100.0)
        }
    }

    private suspend fun transferTheFile() {
        // This is obviously a mock up to exemplify the changes
        repeat(10) { iter ->
            delay(100)
            _state.value = _state.value.copy(percentComplete = 10.0 * iter)
        }
    }

    private suspend fun initializeConnection() = delay(2000)

    companion object {
        private val INITIAL_STATE = DownloadProgress(DownloadStatus.NOT_REQUESTED)
    }
}

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    val model = DownloadModel()
    launch {
        model.state.collect {
            trace("Another Update on the DL progress: $it")
        }
    }
    launch { model.download() }
    launch { model.download() } // Won't trigger DL functionality again, due to the mutex lock during startup
}
