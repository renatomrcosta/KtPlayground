package kt_1_7.def_nn

fun <T : Any?> checkNull(value1: T, value2: T & Any): T & Any = value1 ?: value2

fun main() {
    checkNull<String>("1", "2").run { println(this) } // 1
//    checkNull<String>(null, "2") // null not acceptable

    checkNull<String?>(null, "2").run { println(this) } // 2
//    checkNull<String?>(null, null) // not acceptable
}
