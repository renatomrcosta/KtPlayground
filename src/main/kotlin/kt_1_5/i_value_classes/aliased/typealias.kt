package kt_1_5.i_value_classes.aliased


private typealias MyFirstApiId = String
private typealias MyOtherApiId = String

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
        id = "some-id", name = "some-name"
    )

    val mySecondDto = MyOtherApiDto(
        id = "some-other-id", name = "some-name"
    )
    println(myFirstDto.id)
    println(mySecondDto.id)

    MyFirstApiDto(
        id = mySecondDto.id, name = mySecondDto.name
    )
}
