package dojos.fixture


data class MyFunDto(val color: String = "yellow")

inline fun <reified T> newInstance(): T = T::class.java.getDeclaredConstructor().newInstance()

//class Client() {
//    inline fun <reified TypeParameter> getObject(): TypeParameter {
//        TypeParameter::class.simpleName
//    }
//}


fun main() {
    println("let's play")

//    val client = Client()
//    val listOfMyFunDTO= listOf(client.getObject<MyFunDto>())
//    val result: MyFunDto = Client().getObject()

    newInstance<String?>()
}
