package ijb.adventofcode.`1`

import ijb.adventofcode.`2`.isGamePossible
import ijb.adventofcode.`2`.parseLine
import java.io.File

fun main() {
    val file = "/Users/pPersonal/Dev/ijb.adventofcode/src/main/kotlin/ijb/adventofcode/2/input.txt"
    val bag = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )
    var gameSum: Int = 0

    File(file).forEachLine { line ->
        val game = parseLine(line)

        if (isGamePossible(game, bag)) {
            println("is possible ${line}")
            gameSum += game.id
        } else {
            println("is not possible ${line}")
        }
    }

    println("your result: ${gameSum}")
}