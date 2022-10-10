package kt_1_7_20

private sealed interface Seal1 {
    class Banana(val id: String) : Seal1
    class Banana2(val id: String) : Seal1
    object Nanica : Seal1
}
private sealed interface Seal2 {
    data class Banana(val id: String) : Seal2
    data class Banana2(val id: String) : Seal2
    data object Nanica : Seal2
}

fun main() {
    val value1 = Triple(
        Seal1.Banana("aaaa"),
        Seal1.Banana2("aaaa2"),
        Seal1.Nanica
    )
    val value2 = Triple(
        Seal2.Banana("aaaa"),
        Seal2.Banana2("aaaa2"),
        Seal2.Nanica
    )
    // No data
    //    (kt_1_7_20.Seal1$Banana@7699a589, kt_1_7_20.Seal1$Banana2@58372a00, kt_1_7_20.Seal1$Nanica@4dd8dc3)
    println(value1)

    // data object
    //    (Banana(id=aaaa), Banana2(id=aaaa2), Nanica)
    println(value2)
}
