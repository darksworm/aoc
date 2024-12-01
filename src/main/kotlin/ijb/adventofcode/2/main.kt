package ijb.adventofcode.`2`

import java.io.File
import kotlin.math.abs

fun read(filePath: String): Pair<List<Int>, List<Int>> {
    val leftList = mutableListOf<Int>();
    val rightList = mutableListOf<Int>();

    File(filePath).forEachLine { line ->
        val (l, r) = line.split("   ")
        leftList += l.toInt()
        rightList += r.toInt()
    }

    return Pair(leftList, rightList)
}

fun main() {
    val file = "/Users/ilmars/Dev/ijb.adventofcode/src/main/kotlin/ijb/adventofcode/2/input.txt"

    val input = read(file)

    val similarityScores = input.first.map { firstVal ->
        val matches = input.second.filter{ secondVal -> secondVal == firstVal }
        firstVal * matches.count()
    }

    val sum = similarityScores.sum()

    println("Your result is: $sum")
}
