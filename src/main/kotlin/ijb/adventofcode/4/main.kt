package ijb.adventofcode.`4`

import java.io.File
import kotlin.math.abs

fun read(filePath: String): String {
    return File(filePath).readText(Charsets.UTF_8)
}

enum class Instruction {
    MUL,
    DO,
    DONT
}

fun main() {
    val file = "/Users/ilmars/Dev/ijb.adventofcode/src/main/kotlin/ijb/adventofcode/4/input.txt"

    val content = "do()" + read(file)

    var isOff = false

    val sum = "mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)"
        .toRegex()
        .findAll(content)
        .map {
            Pair(
                if (it.groups[0]?.value?.startsWith("mul") == true)
                    Instruction.MUL
                else if (it.groups[0]?.value?.startsWith("don") == true)
                    Instruction.DONT
                else Instruction.DO,

                it.groups[0]?.value
            )
        }
        .filter { it.second !== null }
        .map { x ->
            if (x.first == Instruction.DO) {
                isOff = false
            } else if (x.first == Instruction.DONT) {
                isOff = true
            }

            if (isOff || x.first !== Instruction.MUL) null else x.second
        }
        .filterNotNull()
        .map { x ->
            x.drop(4)
                .dropLast(1)
                .split(",")
                .map { it.toInt() }
                .reduce(Int::times)
        }
        .sum()

    println("Your result is: $sum")
}
