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

fun isSafe(report: List<Int>, recursionLevel: Int = 0): Boolean {
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
                if (recursionLevel > 0) {
                    return@isSafe false;
                } else {
                    return@isSafe isSafe(
                        report.toMutableList().apply { removeAt(index + 1) },
                        recursionLevel + 1
                    ) || isSafe(
                        report.toMutableList().apply { removeAt(index) },
                        recursionLevel + 1
                    )
                }
            }
        }

        val difference = abs(next - current)

        if (difference > 3 || difference < 1) {
            if (recursionLevel > 0) {
                return@isSafe false;
            }

            if (index == 0) {
                if(isSafe(
                    report.toMutableList().apply { removeAt(index) },
                    recursionLevel + 1)
                    ) {
                    return@isSafe true;
                }
            }

            return@isSafe isSafe(
                report.toMutableList().apply { removeAt(index + 1) },
                recursionLevel + 1
            )
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
