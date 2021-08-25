package dojos.iii_sequences

import util.withExecutionTime

fun main() {
    data class MyDto(val id: Int)

    fun Int.toMyDto() = MyDto(id = this * 100)

    withExecutionTime {
        println("Operating with lists")

        val result = (1..10).toList()
            .filter {
                println("filtering value $it")
                it % 2 == 0
            }
            .map {
                println("mapping value $it")
                Thread.sleep(100)
                it.toMyDto()
            }
            .find { it.id >= 400 }

        println(result)
    }

    withExecutionTime {
        println("Operating with sequences")

        val result = (1..10).toList().asSequence()
            .filter {
                println("filtering value $it")
                it % 2 == 0
            }
            .map {
                println("mapping value $it")
                Thread.sleep(100)
                it.toMyDto()
            }
            .find { it.id >= 400 }



        println(result)
    }
}
