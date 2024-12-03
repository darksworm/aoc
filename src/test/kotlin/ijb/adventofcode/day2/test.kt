package ijb.adventofcode.day2

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*

class Test {

    @Test
    fun `yes`() {
        val inputs = listOf(
            Pair(listOf(7, 6, 4, 2, 1), true),
            Pair(listOf(1, 2, 7, 8, 9), false),
            Pair(listOf(9, 7, 6, 2, 1), false),
            Pair(listOf(1, 3, 2, 4, 5), true),
            Pair(listOf(8, 6, 4, 4, 1), true),
            Pair(listOf(7, 5, 3, 2, 1, 9), true),
        )

        for((input, expected) in inputs) {
            expectThat(isSafe(input))
                .describedAs("For input: $input")
                .isEqualTo(expected)
        }
    }

}
