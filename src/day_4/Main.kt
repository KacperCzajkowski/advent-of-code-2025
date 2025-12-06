package day_4

import java.io.File

const val PART_2 = true

private const val EMPTY_PLACE = '.'
private const val ROLL_SYMBOL = '@'

fun main() {
    var lines = File("src/dayFour/input.txt")
        .readLines()
    val lineLength = lines.first().length
    var totalCount = 0
    val listOfChangesToApply = HashMap<Int, ArrayList<Int>>()
    do {
        listOfChangesToApply.clear()
        repeat(lines.size) { i ->
            val currentLine = lines[i]
            val previousLine = if (i != 0) lines[i - 1] else ".".repeat(lineLength)
            val nextLine = if (i != lines.size - 1) lines[i + 1] else EMPTY_PLACE.toString().repeat(lineLength)

            val currentLineAsList = currentLine.toList()
            val previousLineAsList = previousLine.toList()
            val nextLineAsList = nextLine.toList()
            val currentLineChanges = ArrayList<Int>()
            repeat(lineLength) { j ->
                if (currentLineAsList[j] != EMPTY_PLACE) {
                    if (checkIfCanMeAccessed(currentLineAsList, previousLineAsList, nextLineAsList, j)) {
                        currentLineChanges.add(j)
                    }
                }
            }
            if (currentLineChanges.isNotEmpty()) {
                listOfChangesToApply.put(i, currentLineChanges)
            }
        }

        val localCount = listOfChangesToApply.values.sumOf { it.size }
        totalCount += localCount
        lines = lines.mapIndexed { index, string ->
            val changesToApply = listOfChangesToApply.get(index)
            if (changesToApply == null || changesToApply.isEmpty()) {
                return@mapIndexed string
            }

            val charArray = string.toCharArray()

            for (indexToChange in changesToApply) {
                charArray[indexToChange] = EMPTY_PLACE
            }

            return@mapIndexed charArray.joinToString("")
        }
    } while (PART_2 && localCount != 0)
    println("Found: $totalCount")
}

fun checkIfCanMeAccessed(current: List<Char>, previous: List<Char>, next: List<Char>, index: Int): Boolean {
    var adjacentRollsCount = 0

    if (index > 0) { // first
        adjacentRollsCount += getOneIfCharIsRollOfPaper(previous[index - 1])
        adjacentRollsCount += getOneIfCharIsRollOfPaper(current[index - 1])
        adjacentRollsCount += getOneIfCharIsRollOfPaper(next[index - 1])
    }

    if (index < previous.size - 1) { // last
        adjacentRollsCount += getOneIfCharIsRollOfPaper(previous[index + 1])
        adjacentRollsCount += getOneIfCharIsRollOfPaper(current[index + 1])
        adjacentRollsCount += getOneIfCharIsRollOfPaper(next[index + 1])
    }

    adjacentRollsCount += getOneIfCharIsRollOfPaper(previous[index])
    adjacentRollsCount += getOneIfCharIsRollOfPaper(next[index])

    return adjacentRollsCount < 4
}

fun getOneIfCharIsRollOfPaper(char: Char): Int {
    return if (char == ROLL_SYMBOL) 1 else 0
}