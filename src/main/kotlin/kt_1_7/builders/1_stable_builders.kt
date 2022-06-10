package kt_1_7.builders

fun main() {
    val buildList: List<Int> = buildList {
        add(1)
        add(2)
    }

    val buildMap: Map<String, Int> = buildMap { put("One", 1) }
}
