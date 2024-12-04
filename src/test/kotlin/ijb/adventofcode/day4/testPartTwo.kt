package ijb.adventofcode.day4

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*

class TestPartTwo {

    @Test
    fun `one MAS square is counted once`() {
        val input = """
            M.S
            .A.
            M.S
        """.trimIndent()

        expectThat(partTwo(input)).isEqualTo(1)
    }

    @Test
    fun `provided example input can be counted`() {
        val input = """
            .M.S......
            ..A..MSMS.
            .M.S.MAA..
            ..A.ASMSM.
            .M.S.M....
            ..........
            S.S.S.S.S.
            .A.A.A.A..
            M.M.M.M.M.
            ..........
        """.trimIndent()

        expectThat(partTwo(input)).isEqualTo(9)
    }

}
