package kt_1_4_event.iii_serialization

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import util.trace
import java.util.UUID

@Serializable
private data class Banana(@Serializable(with = UUIDSerializer::class) val id: UUID, val name: String)

fun main() = runBlocking {
    // Encoding
    val banana = Banana(id = UUID.randomUUID(), name = "Rando Rando")
    val encodedStr = Json.encodeToString(banana)
    trace(encodedStr)

    // Decoding

    val decodedPOKO = Json.decodeFromString<Banana>(stringObject)
    trace(decodedPOKO)
}

private val stringObject = """
    {
        "id": "ce4fe228-882a-436a-9f0e-d8ce43089ff6",
        "name": "Bananinha"
    }
""".trimIndent()

object UUIDSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor
        get() = buildClassSerialDescriptor("UUID")

    override fun deserialize(decoder: Decoder): UUID {
        val uuidString = decoder.decodeString().trim()
        return UUID.fromString(uuidString)
    }

    override fun serialize(encoder: Encoder, value: UUID) =
        encoder.encodeString(value.toString())
}
