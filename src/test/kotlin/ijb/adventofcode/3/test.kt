package ijb.adventofcode.`3`

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class Test {
    
    @Test
    fun `splitIntoChunks handles first line properly`() {
        val result = splitIntoChunks(listOf("l1", "l2"))

        expectThat(result)
            .isEqualTo(listOf(
                listOf("..", "l1", "l2"),
                listOf("l1", "l2", "..")
            ))
    }

    @Test
    fun `parseChunks detects adjacent values correctly`() {
        val chunk = listOf(
            "....?.....",
            ".+1...445^.",
            "....?.....",
        )

        expectThat(parseChunks(listOf(chunk)))
            .isEqualTo(listOf(1, 45))
    }

    @Test
    fun `parseChunks detects values that have symbols on top of them`() {
        val chunk = listOf(
            ".......?..",
            "..1...45..",
            "..?.......",
        )

        expectThat(parseChunks(listOf(chunk)))
            .isEqualTo(listOf(1, 45))
    }

    @Test
    fun `parseChunks detects values that have symbols on diagonals of them`() {
        val chunk = listOf(
            ".....?....",
            "..1...45..",
            "...@......",
        )

        expectThat(parseChunks(listOf(chunk)))
            .isEqualTo(listOf(1, 45))
    }

}