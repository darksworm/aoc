package ijb.adventofcode.day2

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
    val file = "/Users/ilmars/Dev/ijb.adventofcode/src/main/kotlin/ijb/adventofcode/day2/input.txt"
    val sum = read(file)
        .map{
            x -> listOf(x) + x.indices.map { index ->
                x.toMutableList().apply { removeAt(index) }
            }
        }
        .map{
            x -> if(x.map{ y -> if (isSafe(y)) 1 else 0 }.sum() > 0) 1 else 0
        }
        .sum();

    println("Part two result is: $sum")
}
