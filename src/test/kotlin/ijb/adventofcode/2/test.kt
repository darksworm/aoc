package ijb.adventofcode.`2`

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*

class Test {

    @Test
    fun `parseLine extracts correct game number`() {
        val result = parseLine("Game 74: ")

        expectThat(result.id)
            .isEqualTo(74)
    }

    @Test
    fun `parseLine extracts a set`() {
        val result = parseLine("Game 66: 3 red, 9 blue; 1 red, 6 blue, 15 green; 3 green, 3 red, 11 blue")

        expectThat(result.sets)
            .isEqualTo(
                listOf(
                    mapOf("red" to 3, "blue" to 9),
                    mapOf("red" to 1, "blue" to 6, "green" to 15),
                    mapOf("red" to 3, "blue" to 11, "green" to 3),
                )
            )
    }

    @Test
    fun `isGamePossible not possible with zero pieces`()  {
        val game = Game(1, listOf(
            mapOf("red" to 3, "blue" to 9),
            mapOf("red" to 1, "blue" to 6, "green" to 15),
            mapOf("red" to 3, "blue" to 11, "green" to 3),
        ))

        val bag = mapOf("red" to 0, "blue" to 0, "green" to 0)

        expectThat(isGamePossible(game, bag))
            .isFalse()
    }

    @Test
    fun `isGamePossible possible with a hundred pieces`()  {
        val game = Game(1, listOf(
            mapOf("red" to 3, "blue" to 9),
            mapOf("red" to 1, "blue" to 6, "green" to 15),
            mapOf("red" to 3, "blue" to 11, "green" to 3),
        ))

        val bag = mapOf("red" to 100, "blue" to 100, "green" to 100)

        expectThat(isGamePossible(game, bag))
            .isTrue()
    }

    @Test
    fun `isGamePossible does shit`()  {
        val game = parseLine("Game 96: 13 blue, 10 red, 2 green; 10 red, 2 green, 1 blue; 6 blue, 5 red, 3 green; 11 red, 3 green, 5 blue; 11 red, 2 green; 3 green, 6 blue")
        val bag = mapOf("red" to 12, "blue" to 13, "green" to 14)

        expectThat(isGamePossible(game, bag))
            .isTrue()
    }

}