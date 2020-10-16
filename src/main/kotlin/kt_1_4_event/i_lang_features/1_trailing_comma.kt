package kt_1_4_event.i_lang_features

class MyFunnyClass() {
    fun myFun(arg1: String, arg2: Int, arg3: List<String>) = println("hi there!")
}

fun main() {
    // Really convenient to change signatures / invocation order
    // Also mixed style, if you wanna
    MyFunnyClass().myFun(
        "",
        arg3 = listOf(),
        arg2 = 0,
    )
}

