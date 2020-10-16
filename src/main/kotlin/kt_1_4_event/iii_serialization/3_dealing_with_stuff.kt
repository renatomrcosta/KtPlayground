package kt_1_4_event.iii_serialization

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import util.trace

@Serializable
sealed class User {
    abstract val id: String
    abstract val name: String
    abstract val details: List<UserDetail>

    @Serializable
    data class Admin(
        override val id: String,
        override val name: String,
        override val details: List<UserDetail>,
        val permissionLevel: Int
    ) : User()

    @Serializable
    data class Pleb(
        override val id: String,
        override val name: String,
        override val details: List<UserDetail>,
        val eatsBananas: Boolean = false,
    ) : User()
}

@Serializable
data class UserDetail(val type: String, val description: String)

fun main() = runBlocking {
    // Encoding
    val adminUser = User.Admin(
        id = "1", name = "2", details = listOf(), permissionLevel = 666
    )
    val encodedStr = Json.encodeToString<User>(adminUser)
    trace(encodedStr)

    // Decoding

    val decodedPOKO = Json {
        coerceInputValues = true
    }.decodeFromString<User>(stringObject)

    trace(decodedPOKO)
}

private val stringObject = """
    {
        "type": "kt_1_4_event.iii_serialization.User.Pleb",
        "id": "12",
        "name": "Bananinha",
        "details": [
            { "type": "coiso", "description": "something something"}
        ],
        "eatsBananas": null
    }
""".trimIndent()
