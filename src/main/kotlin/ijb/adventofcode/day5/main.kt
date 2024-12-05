package ijb.adventofcode.day5

import java.io.File

fun read(filePath: String): Pair<String, String> {
    val content = File(filePath).readText(Charsets.UTF_8).trim()

    val split = content.split("\n\n")

    assert(split.size == 2)

    return Pair(split[0], split[1])
}

fun rulesToMap(rules: String): Map<Int, List<Int>> {
    val result = mutableMapOf<Int, MutableList<Int>>()

    rules
        .split("\n")
        .map {
            it.split("|").map(String::toInt)
        }
        .forEach {
            if (!result.containsKey(it[0])) {
                result[it[0]] = mutableListOf(it[1])
            } else {
                result[it[0]]!!.add(it[1])
            }
        }

    return result
}

fun updatesToList(updates: String): List<Array<Int>> {
    return updates
            .split("\n")
            .map{
                it.split(",").map(String::toInt).toTypedArray()
            }
}

fun isValidUpdate(ruleMap: Map<Int, List<Int>>, update: Array<Int>): Boolean {
    update.forEachIndexed { index, value ->
        val cannotPrecedeList = ruleMap[value]

        assert(cannotPrecedeList != null)

        if (index == 0) {
            return@forEachIndexed
        }

        update
            .copyOfRange(0, index - 1)
            .forEach { subsequentValue ->
                if (cannotPrecedeList!!.firstOrNull{ it == subsequentValue } != null) {
                    return@isValidUpdate false
                }
            }
    }

    return true;
}

fun partOne(filePath: String): Int {
    val (rules, updates) = read(filePath)

    val ruleMap = rulesToMap(rules)
    val updatesList = updatesToList(updates)

    return updatesList
        .filter {
            isValidUpdate(ruleMap, it)
        }.sumOf {
            assert(it.size % 2 == 0)
            it[it.size / 2]
        }
}

fun partTwo(filePath: String): Int {
    read(filePath)

    return 0;
}

fun main() {
    val file = "./src/main/kotlin/ijb/adventofcode/day5/input.txt"

    println("Part one result is: ${partOne(file)}")
    println("Part two result is: ${partTwo(file)}")
}
