package kt_1_5.i_value_classes.valueclass


@JvmInline
private value class MyFirstApiId(private val id: Int)

@JvmInline
private value class MyOtherApiId(private val id: Int)

private data class MyFirstApiDto(
    val id: MyFirstApiId,
    val name: String
)

private data class MyOtherApiDto(
    val id: MyOtherApiId,
    val name: String
)

fun main() {
    val myFirstDto = MyFirstApiDto(
        id = MyFirstApiId(100), name = "some-name"
    )

    val mySecondDto = MyOtherApiDto(
        id = MyOtherApiId(100), name = "some-name"
    )
    println(myFirstDto.id)
    println(mySecondDto.id)

    // Does not compile
//    if(myFirstDto.id == mySecondDto.id)
    // Also does not compile
//    MyFirstApiDto(
//        id = MyFirstApiId(mySecondDto.id),
//        name = mySecondDto.name
//    )
}
