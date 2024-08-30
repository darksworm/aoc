package ijb.adventofcode.`1`

import java.io.File

fun main() {
    val file = "/Users/pPersonal/Dev/ijb.adventofcode/src/main/kotlin/ijb/adventofcode/1/input.txt"

    var totalNumber = 0;

    File(file).forEachLine { line ->
        var number: String  = ""
        var lastNumberDigit: String = ""

        line.chars().forEach{ character ->
            if (!Character.isDigit(character)) {
                return@forEach
            }

            lastNumberDigit = character.toChar().toString()

            if (number.isEmpty()) {
                number = lastNumberDigit
            }
        }

        totalNumber += "${number}${lastNumberDigit}".toInt()
    }

    println("your result: ${totalNumber}")
}