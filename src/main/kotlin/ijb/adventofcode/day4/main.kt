package ijb.adventofcode.day4

import java.io.File

fun read(filePath: String): String {
    return File(filePath).readText(Charsets.UTF_8)
}

fun partTwo(input: String): Int {
    // convert array into 2d array
    val array = input.split("\n")
        .filterNot{ it.isEmpty() } // last line is probably empty
        .map {
            it.split("").toTypedArray()
        }
        .toTypedArray()

    return 0;
}

fun partOne(input: String): Int {
    // convert array into 2d array
    val array = input.split("\n")
        .filterNot{ it.isEmpty() } // last line is probably empty
        .map {
            it.split("").toTypedArray()
        }
        .toTypedArray()

    val allXLocations = array.mapIndexed{ upperIndex, upperElem ->
        upperElem.mapIndexed{ index, elem -> if (elem == "X") Pair(upperIndex, index) else null }.filterNotNull()
    }.flatten()

    var counter = 0;

    for ((y, x) in allXLocations) {
        // detect horizontal LR XMAS
        if (x + 3 < array[y].size && array[y].copyOfRange(x, x + 4).joinToString("") == "XMAS") {
            counter++
        }

        // detect horizontal RL XMAS (SAMX)
        if (x - 3 >= 0 && array[y].copyOfRange(x - 3, x + 1).joinToString("").reversed() == "XMAS") {
            counter++
        }

        // detect vertical XMAS TB
        if (y + 3 < array.size && array[y][x] + array[y + 1][x] + array[y + 2][x] + array[y + 3][x] == "XMAS") {
            counter++
        }

        // detect vertical XMAS BT
        if (y - 3 >= 0 && array[y][x] + array[y - 1][x] + array[y - 2][x] + array[y - 3][x] == "XMAS") {
            counter++
        }

        // detect diagonal XMAS LR TB
        if (y + 3 < array.size && x + 3 < array[y + 3].size && array[y][x] + array[y + 1][x + 1] + array[y + 2][x + 2] + array[y + 3][x + 3] == "XMAS") {
            counter++
        }

        // detect diagonal XMAS LR BT
        if (y - 3 >= 0 && x - 3 >= 0 && array[y][x] + array[y - 1][x - 1] + array[y - 2][x - 2] + array[y - 3][x - 3] == "XMAS") {
            counter++
        }

        // detect diagonal XMAS RL TB
        if (y + 3 < array.size && x - 3 >= 0 && array[y][x] + array[y + 1][x - 1] + array[y + 2][x - 2] + array[y + 3][x - 3] == "XMAS") {
            counter++
        }

        // detect diagonal XMAS RL BT
        if (y - 3 >= 0 && x + 3 < array[y - 3].size && array[y][x] + array[y - 1][x + 1] + array[y - 2][x + 2] + array[y - 3][x + 3] == "XMAS") {
            counter++
        }
    }

    return counter;
}

fun main() {
    val file = "./src/main/kotlin/ijb/adventofcode/day4/input.txt"
    val content = read(file)

    println("Part one result is: ${partOne(content)}")
    println("Part two result is: ${partTwo(content)}")
}
