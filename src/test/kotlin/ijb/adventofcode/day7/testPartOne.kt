package ijb.adventofcode.day7

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*

class TestPartOne {

    @Test
    fun `provided sample is calculated corretly`() {
        val input = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
        """.trimIndent()
            .split(System.lineSeparator())
            .map {
                it.split(" ", ": ")
                    .let { x -> Equation(x[0].toLong(), x.drop(1).map(String::toInt)) }
            }

        expectThat(partOne(input)).isEqualTo(3749)
    }
}
