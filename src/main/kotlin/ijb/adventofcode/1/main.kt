package ijb.adventofcode.`1`

import java.io.File

fun main() {
    val mapping = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    val file = "/Users/pPersonal/Dev/ijb.adventofcode/src/main/kotlin/ijb/adventofcode/1/input.txt"
    var totalNumber = 0;

    File(file).forEachLine { line ->
        var earliestPosition: Int = line.length + 99
        var earliestDigit: String = ""

        var lastPosition: Int = 0
        var lastDigit: String = ""

        for((text, numberDigit) in mapping) {
            val textPos = line.indexOf(text)
            val digitPos = line.indexOf(numberDigit)

            if (textPos != -1) {
                val textPosLast = line.lastIndexOf(text)

                if (earliestPosition > textPos) {
                    earliestDigit = numberDigit
                    earliestPosition = textPos
                }

                if (lastPosition < textPos) {
                    lastDigit = numberDigit
                    lastPosition = textPos
                }

                if (lastPosition < textPosLast) {
                    lastDigit = numberDigit
                    lastPosition = textPosLast
                }
            }

            if (digitPos != -1) {
                val digitPosLast = line.lastIndexOf(numberDigit)

                if (earliestPosition > digitPos) {
                    earliestDigit = numberDigit
                    earliestPosition = digitPos
                }

                if (lastPosition < digitPos) {
                    lastDigit = numberDigit
                    lastPosition = digitPos
                }

                if (lastPosition < digitPosLast) {
                    lastDigit = numberDigit
                    lastPosition = digitPosLast
                }
            }
        }

        if (lastDigit.isEmpty()) {
            lastDigit = earliestDigit
        }

        println("line ${line} firstDigit ${earliestDigit} lastDigit ${lastDigit}")

        totalNumber += "${earliestDigit}${lastDigit}".toInt()
    }

    println("your result: ${totalNumber}")
}