package ijb.adventofcode.day6

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*

class TestPartOne {

    @Test
    fun `provided sample is calculated corretly`() {
        val input = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
        """.trimIndent()

        expectThat(partOne(input.split("\n"))).isEqualTo(41)
    }
}
