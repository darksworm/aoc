package ijb.adventofcode.`3`

import java.io.File

fun main() {
    val file = "/Users/pPersonal/Dev/ijb.adventofcode/src/main/kotlin/ijb/adventofcode/3/input.txt"
    val lines = File(file).readLines()

    val partNumbers = parseChunks(
        splitIntoChunks(lines)
    )

    println(partNumbers)

    val result = partNumbers.fold(0) { acc, i -> acc + i }

    println("your answer is $result")
}
