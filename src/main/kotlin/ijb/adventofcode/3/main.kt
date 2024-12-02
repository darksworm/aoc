package ijb.adventofcode.`3`

import java.io.File
import kotlin.math.abs

fun read(filePath: String): List<List<Int>> {
    val list = mutableListOf<List<Int>>()

    File(filePath).forEachLine { line ->
        list.add(line.split(" ").map { x -> x.toInt() })
    }

    return list
}

fun isSafe(report: List<Int>): Boolean {
    var isIncreasing: Boolean? = null

    report.withIndex().forEach{ (index, current) ->
        if (index + 1 >= report.size) {
            return@forEach;
        }

        val next = report[index + 1]

        if (isIncreasing == null)  {
            isIncreasing = next > current
        } else {
            if (isIncreasing != next > current) {
                return@isSafe false;
            }
        }

        val difference = abs(next - current)

        if (difference > 3 || difference < 1) {
            return@isSafe false;
        }
    }

    return true;
}

fun main() {
    val file = "/Users/ilmars/Dev/ijb.adventofcode/src/main/kotlin/ijb/adventofcode/3/input.txt"

    val sum = read(file)
        .map{ x -> isSafe(x) }
        .map{ x -> if (x) 1 else 0 }
        .sum()

    println("Your result is: $sum")
}
