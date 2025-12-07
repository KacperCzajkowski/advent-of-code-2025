package day_6

import java.io.File

fun main() {
    val lines = File("src/day_6/input.txt").readLines()
    val grid = lines.map { line ->
        val splitted = line.split(" ")
        splitted.filter { it.isNotEmpty() }.map { it.trim() }
    }
    val mutableLines: MutableList<String> = lines.toMutableList()
    val stringGrid = ArrayList<ArrayList<String>>()
    repeat(mutableLines.size - 1) {
        stringGrid.add(ArrayList())
    }
    repeat(grid[0].size) { index ->
        val maxStringLength: Int = getMaxFromColumn(grid, index)
        for (j in 0..<mutableLines.size - 1) {
            if (mutableLines[j].length < maxStringLength) {
                mutableLines[j] = mutableLines[j].plus(" ".repeat(maxStringLength - mutableLines[j].length))
            }
            stringGrid[j].add(mutableLines[j].substring(0, maxStringLength))
            mutableLines[j] = mutableLines[j].drop(maxStringLength + 1)
        }
    }

    val operations = grid.last()
    var finalResult: Long = 0
    repeat(stringGrid[0].size) { column ->
        var columnResult: Long = if (operations[column] == "*") 1 else 0
        repeat(stringGrid[0][column].length) { stringColumn ->
            val stringBuilder = StringBuilder()
            repeat(stringGrid.size) { row ->
                val ch = stringGrid[row][column][stringColumn]
                if (ch.isDigit()) {
                    stringBuilder.append(ch)
                }
            }
            val stringAsLong = stringBuilder.toString().toLong()
            if (operations[column] == "*") {
                columnResult *= stringAsLong
            } else {
                columnResult += stringAsLong
            }
        }
        finalResult += columnResult
    }
    println("Result $finalResult")
}

private fun getMaxFromColumn(grid: List<List<String>>, index: Int): Int {
    var currentMax = grid[0][index].length
    for (i in 1..<grid.size - 1) {
        if (currentMax < grid[i][index].length) {
            currentMax = grid[i][index].length
        }
    }

    return currentMax
}
