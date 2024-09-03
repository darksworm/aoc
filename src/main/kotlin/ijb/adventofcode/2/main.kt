package ijb.adventofcode.`2`

import java.io.File

fun main() {
    val file = "/Users/pPersonal/Dev/ijb.adventofcode/src/main/kotlin/ijb/adventofcode/2/input.txt"
    val bag = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14
    )

    var firstChallengeAnswer: Int = 0
    var secondChallengeAnswer: Int = 0

    File(file).forEachLine { line ->
        val game = parseLine(line)

        if (isGamePossible(game, bag)) {
            firstChallengeAnswer += game.id
        }

        val minimumBag = calculateMinimumBag(game)
        secondChallengeAnswer += minimumBag["red"]!! * minimumBag["green"]!! * minimumBag["blue"]!!
    }

    println("firstChallenge result: ${firstChallengeAnswer}")
    println("secondChallenge result: ${secondChallengeAnswer}")
}