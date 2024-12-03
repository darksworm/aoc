package ijb.adventofcode.template

import java.io.File

// search replace template -> dayx

fun read(filePath: String) {
    File(filePath)
}

fun partOne(filePath: String): Int {
    read(filePath)

    return 0;
}

fun partTwo(filePath: String): Int {
    read(filePath)

    return 0;
}

fun main() {
    val file = "./src/main/kotlin/ijb/adventofcode/template/input.txt"

    println("Part one result is: ${partOne(file)}")
    println("Part two result is: ${partTwo(file)}")
}
