package dojos.fixture

class MyKtClient {
    fun get(url: String): String = "somevalue"
    inline fun <reified T : Any> getObject(url: String): T =
        when (T::class) {
            String::class -> "Banana" as T
            else -> T::class.java.getDeclaredConstructor().newInstance()
        }
}

inline fun <reified T: Number> T.explode(): T = (this.toDouble() * 1000) as T

data class MyKtDTO(val id: String = "default")

fun main() {
    val client = MyKtClient()
    val response1: MyKtDTO = client.getObject("someUrl")
    val response2: String = client.getObject("someUrl")
    println(response1)
    println(response2)

    println(123.3.explode())
}
