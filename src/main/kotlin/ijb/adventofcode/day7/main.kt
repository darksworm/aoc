package ijb.adventofcode.day7

import java.io.File
import kotlin.time.measureTime

data class Equation(val product: Long, val factors: List<Long>)
typealias Operator = (Long, Long) -> Long

// 666 + 777 = 666000 + 777
fun Long.fastConcat(num: Long): Long {
    var multiplier = 1L
    var temp = num
    while (temp > 0) {
        temp /= 10
        multiplier *= 10
    }
    return this * multiplier + num
}

val ops: List<Operator> = listOf(
    { a, b -> a + b },
    { a, b -> a * b },
    { a, b -> a.fastConcat(b) }
)

fun read(filePath: String): List<Equation> =
    File(filePath)
        .readText(Charsets.UTF_8)
        .trim()
        .split(System.lineSeparator())
        .map {
            it.split(" ", ": ")
                .let { x -> Equation(x[0].toLong(), x.drop(1).map(String::toLong)) }
        }

private val operatorCombosCache = mutableMapOf<Pair<Int, Int>, List<List<Operator>>>()

fun generateOperatorCombos(factorCount: Int, ops: List<Operator>): Sequence<List<Operator>> {
    val operatorCount = factorCount - 1
    if (operatorCount <= 0) return emptySequence()

    val key = Pair(factorCount, ops.size)

    if (operatorCombosCache[key] == null) {
        operatorCombosCache[key] = (1..operatorCount)
            .fold(listOf(emptyList())) { acc, _ ->
                acc.flatMap { combo ->
                    ops.map { combo + it }
                }
            }
    }

    return operatorCombosCache[key]!!.asSequence()
}

fun applyOperators(eq: Equation, operators: List<Operator>): Long {
    require(eq.factors.size == operators.size + 1) { "Operators must be one less than values." }

    return eq.factors
        .drop(1)
        .zip(operators)
        .fold(eq.factors.first()) { acc, (value, op) ->
            if (acc > eq.product) return@applyOperators -1 else op(acc, value)
        }
}

fun processEquations(eqs: List<Equation>, ops: List<Operator>): Long =
    eqs.sumOf { eq ->
        generateOperatorCombos(eq.factors.size, ops)
            .map { applyOperators(eq, it) }
            .firstOrNull { it == eq.product }
            ?: 0
    }

fun partOne(eqs: List<Equation>): Long =
    processEquations(eqs, ops.take(2))

fun partTwo(eqs: List<Equation>) =
    processEquations(eqs, ops)

fun main() {
    val file = "./src/main/kotlin/ijb/adventofcode/day7/input.txt"
    val content = read(file)

    println("Part one result is: ${partOne(content)}")

    val timeTaken = measureTime {
        println("Part two result is: ${partTwo(content)}")
    }

    println("Part two took $timeTaken")
}
