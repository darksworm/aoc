package ijb.adventofcode.day7

import java.io.File
import kotlin.time.measureTime

data class Equation(val product: Long, val factors: List<Int>)
typealias Operator = (Long, Int) -> Long

fun read(filePath: String): List<Equation> =
    File(filePath)
        .readText(Charsets.UTF_8)
        .trim()
        .split(System.lineSeparator())
        .map {
            it.split(" ", ": ")
                .let { x -> Equation(x[0].toLong(), x.drop(1).map(String::toInt)) }
        }

fun generateOperatorCombos(factorCount: Int): List<List<Operator>> {
    val operatorCount = factorCount - 1
    if (operatorCount <= 0) {
        return emptyList()
    }

    val ops: List<(Long, Int) -> Long> = listOf(
        { a, b -> a + b },
        { a, b -> a * b }
    )
    val comboCount = 1 shl operatorCount // 2 ^ (operatorCount)

    return List(comboCount) { mask ->
        List(operatorCount) { i ->
            ops[(mask shr i) and 1]
        }
    }
}

fun applyOperators(eq: Equation, operators: List<Operator>): Long {
    require(eq.factors.size == operators.size + 1) { "Operators must be one less than values." }

    return eq.factors
        .drop(1)
        .zip(operators)
        .fold(eq.factors.first().toLong()) { acc, (value, op) ->
            if (acc > eq.product) return@applyOperators -1 else op(acc, value)
        }
}

fun partOne(eqs: List<Equation>): Long =
    eqs.sumOf { eq ->
        generateOperatorCombos(eq.factors.size)
            .asSequence()
            .map { applyOperators(eq, it) }
            .firstOrNull { it == eq.product }
            ?: 0
    }

fun partTwo(eqs: List<Equation>): Int {
    return -1
}

fun main() {
    val file = "./src/main/kotlin/ijb/adventofcode/day7/input.txt"
    val content = read(file)

    println("Part one result is: ${partOne(content)}")

    val timeTaken = measureTime {
        println("Part two result is: ${partTwo(content)}")
    }

    println("Part two took $timeTaken")
}
