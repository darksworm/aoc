package ijb.adventofcode.`3`

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
            Pair(listOf(1, 3, 6, 7, 9), true),
            Pair(listOf(1, 3, 7, 1, 9), false),
            Pair(listOf(1, 3, 0, 4, 5), true),
            Pair(listOf(1, 3, 0, 0, 5), false),
            Pair(listOf(5, 4, 3, 2, 1), true),
            Pair(listOf(8, 9, 10, 15, 16, 17), false),
            Pair(listOf(89, 92, 94, 96, 97, 99), true),
            Pair(listOf(99, 1, 2, 3, 4, 5), true),
            Pair(listOf(99, 1, 2, 3, 4, 5), true),
            Pair(listOf(9, 5, 6, 7, 8, 9), true),
            Pair(listOf(9, 5, 3, 2, 1, 0), true),
            Pair(listOf(7, 5, 3, 2, 1, 9), true),
            Pair(listOf(7, 5, 6, 2, 1, 0), true),
        )

        for((input, expected) in inputs) {
            expectThat(isSafe(input))
                .describedAs("For input: $input")
                .isEqualTo(expected)
        }
    }

}
