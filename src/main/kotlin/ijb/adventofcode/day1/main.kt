package ijb.adventofcode.day1

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

fun sort(input: Pair<List<Int>, List<Int>>): Pair<List<Int>, List<Int>> {
    return Pair(
        input.first.sorted(),
        input.second.sorted(),
    )
}

fun partOne(file: String): Int {
    val input = read(file)
    val sorted = sort(input)

    assert(sorted.first.size == sorted.second.size)

    val differences = sorted.first.mapIndexed { index, firstVal ->
        abs(firstVal - sorted.second[index])
    }

    return differences.sum()
}

fun partTwo(file: String): Int {
    val input = read(file)

    val similarityScores = input.first.map { firstVal ->
        val matches = input.second.filter{ secondVal -> secondVal == firstVal }
        firstVal * matches.count()
    }

    return similarityScores.sum()
}

fun main() {
    val file = "/Users/ilmars/Dev/ijb.adventofcode/src/main/kotlin/ijb/adventofcode/day1/input.txt"

    println("Part one result is: ${partOne(file)}")
    println("Part two result is: ${partTwo(file)}")
}
