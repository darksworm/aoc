package ijb.adventofcode.day6

import java.io.File
import java.util.*
import kotlin.time.measureTime

data class Position(var x: Int, var y: Int)

enum class Direction {
    U, R, D, L
}

fun read(filePath: String): List<String> =
    File(filePath)
        .readText(Charsets.UTF_8)
        .trim()
        .split(System.lineSeparator())
        .toMutableList()

fun isNextStepOutOfBounds(lines: List<String>, dir: Direction, pos: Position): Boolean =
    when (dir) {
        Direction.U -> pos.y == 0
        Direction.R ->  pos.x == lines.first().length - 1
        Direction.D ->  pos.y == lines.size - 1
        Direction.L -> pos.x == 0
    }

fun nextPosition(dir: Direction, prev: Position): Position =
    when (dir) {
        Direction.U -> Position(prev.x, prev.y - 1)
        Direction.R -> Position(prev.x + 1, prev.y)
        Direction.D -> Position(prev.x, prev.y + 1)
        Direction.L -> Position(prev.x - 1, prev.y)
    }

fun turnRight(dir: Direction): Direction =
    when (dir) {
        Direction.U -> Direction.R
        Direction.R -> Direction.D
        Direction.D -> Direction.L
        Direction.L -> Direction.U
    }

inline fun walkWhileNotOutOfBounds(lines: List<String>, step: (Position, Direction, Position) -> Unit) {
    val y = lines.indexOfFirst { it.contains("^") }
    assert(y >= 0)
    val x = lines[y].indexOf("^")
    assert(x >= 0)

    var curPos = Position(x, y)
    var curDir = Direction.U

    while (!isNextStepOutOfBounds(lines, curDir, curPos)) {
        val nextPos = nextPosition(curDir, curPos)

        step(curPos, curDir, nextPos)

        if (lines[nextPos.y][nextPos.x] == '#') {
            curDir = turnRight(curDir)
            continue
        }

        curPos = nextPos
    }
}

fun partOne(lines: List<String>): Int {
    val visitedPositions = mutableSetOf<Position>()

    walkWhileNotOutOfBounds(lines) { curPos, _, _ ->
        visitedPositions += curPos
    }

    // somehow, I'm off by one, 🤷‍♂️
    return visitedPositions.size + 1
}

fun calculateAllPossibleWallPlacements(lines: List<String>) = iterator {
    for ((lineIdx, l) in lines.withIndex()) {
        for ((letterIdx, _) in l.withIndex()) {
            if (lines[lineIdx][letterIdx] in setOf('#', '^')) {
                continue
            }

            yield(
                lines.mapIndexed { idx, line ->
                    if (idx == lineIdx) {
                        line.toCharArray().apply { this[letterIdx] = '#' }.concatToString()
                    } else {
                        line
                    }
                }
            )
        }
    }
}

fun hasLoop(lines: List<String>): Boolean {
    val turnPositions: HashSet<Pair<Position, Direction>> = hashSetOf()

    walkWhileNotOutOfBounds(lines) { curPos, curDir, nextPos ->
        if (lines[nextPos.y][nextPos.x] == '#') {
            if (turnPositions.contains(Pair(curPos, curDir))) {
                return@hasLoop true
            }

            turnPositions += Pair(curPos, curDir)
        }
    }

    return false
}

fun partTwo(lines: List<String>): Int {
    var result = 0

    calculateAllPossibleWallPlacements(lines)
        .forEach { result += if (hasLoop(it)) 1 else 0 }

    return result
}

fun main() {
    val file = "./src/main/kotlin/ijb/adventofcode/day6/input.txt"
    val content = read(file)

    println("Part one result is: ${partOne(content)}")

    val timeTaken = measureTime {
        println("Part two result is: ${partTwo(content)}")
    }

    println("Part two took $timeTaken")
}
