package ijb.adventofcode.`3`

fun splitIntoChunks(lines: List<String>): List<List<String>> {
    val chunks = mutableListOf<List<String>>()

    val defaultValue = lines[0].map{ _ -> "." }.joinToString("")
    var previousLine = defaultValue

    lines.forEachIndexed() { i, line ->
        chunks.add(listOf(
            previousLine,
            line,
            lines.getOrElse(i + 1) { defaultValue }
        ))

        previousLine = line
    }

    return chunks
}

fun parseChunks(chunks: List<List<String>>): List<Int> {
    return chunks.map { lines ->
        assert(lines.size == 3)

        val firstLine = stripNumbers(symbolsToX(lines[0]))
        val middleLine = symbolsToX(lines[1])
        val bottomLine = stripNumbers(symbolsToX(lines[2]))

        Regex("\\d+")
            .findAll(middleLine)
            .map{ matchResult -> matchResult.value to matchResult.range.first }
            .map regexMap@ { (value, position) ->
                val intValue = value.toInt()

                if (middleLine.getOrNull(position - 1) == 'x') {
                    return@regexMap intValue
                }
                if (middleLine.getOrNull(position + value.length) == 'x') {
                    return@regexMap intValue
                }

                for (index in -1 until value.length + 1) {
                    if (firstLine.getOrNull(position + index) == 'x') {
                       return@regexMap intValue
                    }
                    if (bottomLine.getOrNull(position + index) == 'x') {
                        return@regexMap intValue
                    }
                }

                null
            }
            .filterNotNull()
            .toList()
    }.flatten()
}

fun symbolsToX(str: String): String {
    return str.replace(Regex("[^0-9.]"), "x")
}

fun stripNumbers(str: String): String {
    return str.replace(Regex("\\d"), ".")
}