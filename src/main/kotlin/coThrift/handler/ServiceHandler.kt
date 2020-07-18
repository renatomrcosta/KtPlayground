package coThrift.handler

import com.xunfos.playground.GetUserRequest
import com.xunfos.playground.GetUserResponse
import com.xunfos.playground.GetUsersResponse
import com.xunfos.playground.PlaygroundService
import com.xunfos.playground.User
import util.trace
import java.util.UUID
import kotlin.random.Random

class ServiceHandler : PlaygroundService.Iface {
    private val rng = Random(System.currentTimeMillis())
    private fun nextRng() = rng.nextLong(10L, 500L)

    override fun doWork() {
        trace("Starting do work function")
        Thread.sleep(nextRng())
        trace("Finishing do work function")
    }

    override fun ping() {
        trace("Ping!")
    }

    override fun getUser(request: GetUserRequest): GetUserResponse {
        trace("GetUser request started")
        Thread.sleep(nextRng())
        trace("GetUser request finished")

        return GetUserResponse().apply {
            user = User().apply {
                this.id = request.id
                this.name = "Bananinha"
            }
        }
    }

    override fun getUsers(): GetUsersResponse {
        trace("GetUsers request started")
        Thread.sleep(nextRng())
        trace("GetUsers request finished")

        return GetUsersResponse().apply {
            users = listOf(
                User().apply {
                    this.id = UUID.randomUUID().toString()
                    this.name = "Bananinha"
                }
            )
        }
    }
}
