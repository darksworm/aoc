package ijb.adventofcode.day3

import java.io.File

fun read(filePath: String): String {
    return File(filePath).readText(Charsets.UTF_8)
}

enum class Instruction {
    MUL,
    DO,
    DONT
}

fun main() {
    val file = "./src/main/kotlin/ijb/adventofcode/day3/input.txt"

    val content = read(file)

    var isOff = false

    val sum = "mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)"
        .toRegex()
        .findAll(content)
        .map {
            Pair(
                when {
                    it.groups[0]?.value?.startsWith("mul") == true -> Instruction.MUL
                    it.groups[0]?.value?.startsWith("don") == true -> Instruction.DONT
                    else -> Instruction.DO
                },
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

    println("Part two result is: $sum")
}
