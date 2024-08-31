package ijb.adventofcode.`2`

data class Game(
    val id: Int,
    val sets: List<Map<String, Int>>
)

fun parseLine(line: String): Game {
    val firstColonPos = line.indexOf(':')
    val gameNumber = line.substring("Game ".length, firstColonPos)

    val lineWithoutGame = line.substring(firstColonPos + 1).trim()
    val sets = lineWithoutGame.split(';').map{ setString ->
        setString.split(',').map { s -> s.trim() }.associate { cubeString ->
            val separatorPos = cubeString.indexOf(' ')
            val number = cubeString.substring(0, separatorPos)

            val color = cubeString.substring(separatorPos + 1)

            Pair(color, number.toInt())
        }
    }

    return Game(
        gameNumber.toInt(),
        sets
    )
}

fun isGamePossible(game: Game, bag: Map<String, Int>): Boolean {
    game.sets.forEach{ set ->
        bag.forEach{ (color, available) ->
            val used = set.getOrDefault(color, 0)

            if (used > available) {
                return@isGamePossible false
            }
        }
    }

    return true
}