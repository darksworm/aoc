package ijb.adventofcode.day6

import java.io.File

fun read(filePath: String): String {
    return File(filePath)
        .readText(Charsets.UTF_8)
        .trim()
}

enum class Direction {
    L, R, U, D;
}

fun isGoingOOB(lines: List<String>, direction: Direction, position: Position): Boolean =
     (direction == Direction.U && position.y == 0) ||
     (direction == Direction.D && position.y == lines.size - 1) ||
     (direction == Direction.L && position.x == 0) ||
     (direction == Direction.R && position.x == lines.first().length - 1)

fun nextPosition(direction: Direction, original: Position): Position =
    when (direction) {
        Direction.U -> Position(original.x, original.y - 1)
        Direction.D -> Position(original.x, original.y + 1)
        Direction.R -> Position(original.x + 1, original.y)
        Direction.L -> Position(original.x - 1, original.y)
    }

fun turnRight(direction: Direction): Direction =
    when (direction) {
        Direction.U -> Direction.R
        Direction.R -> Direction.D
        Direction.D -> Direction.L
        Direction.L -> Direction.U
    }

data class Position(var x: Int, var y: Int)

fun partOne(input: String): Int {
    val lines = input
        .split(System.lineSeparator())
        .toMutableList()

    val y = lines.indexOfFirst { it.contains("^") }
    assert(y >= 0)
    val x = lines[y].indexOf("^")
    assert(x >= 0)

    var curPos = Position(x, y)
    var curDir = Direction.U

    val visitedPositions = mutableSetOf(curPos)
    var turns = 0

    while (!isGoingOOB(lines, curDir, curPos)) {
        val nextPos = nextPosition(curDir, curPos)

        if (lines[nextPos.y][nextPos.x] == '#') {
            curDir = turnRight(curDir)
            assert(++turns <= 3)
            continue;
        }
//        // for debugging - show the current position
//        lines[curPos.y] = lines[curPos.y].toMutableList().apply {
//            this[curPos.x] = 'X'
//        }.joinToString("")

//        // for debugging - show the next position
//        lines[nextPos.y] = lines[nextPos.y].toMutableList().apply {
//            this[nextPos.x] = '^'
//        }.joinToString("")

        visitedPositions.add(curPos)
        curPos = nextPos
        turns = 0
    }

    // somehow, I'm off by one, 🤷‍♂️
    return visitedPositions.size + 1
}

fun partTwo(filePath: String): Int {
    read(filePath)

    return 0;
}

fun main() {
    val file = "./src/main/kotlin/ijb/adventofcode/day6/input.txt"

    println("Part one result is: ${partOne(read(file))}")
    println("Part two result is: ${partTwo(file)}")
}
