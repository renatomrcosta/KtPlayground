package kt_1_4_event.iii_serialization

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import util.trace

@Serializable
private data class BasicDTO(val id: Int, val name: String, val tags: List<String>)

fun main() = runBlocking {
    // Encoding
    val myDTO = BasicDTO(id = 0, name = "Banana", tags = listOf("Hungry", "Tired"))
    val encodedDTO = Json.encodeToString(myDTO)
    trace(encodedDTO)

    // Decoding
    val decodedDTO: BasicDTO = Json.decodeFromString(stringObject)
    trace(decodedDTO)
}

private val stringObject = """
    {
        "id": 12,
        "name": "Bananinha",
        "tags": ["Full", "Hearty!"]
    }
""".trimIndent()
