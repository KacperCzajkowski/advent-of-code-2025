package dayFour

import java.io.File

const val PART_22 = false
private const val EMPTY_PLACE = '.'
private const val ROLL_SYMBOL = '@'

fun main() {
    var grid = File("src/dayFour/input.txt").readLines()
    var totalCount = 0

    do {
        val positionsToRemove = findRemovablePositions(grid)
        totalCount += positionsToRemove.values.sumOf { it.size }
        grid = applyChanges(grid, positionsToRemove)
    } while (PART_22 && positionsToRemove.isNotEmpty())

    println("Found: $totalCount")
}

private fun findRemovablePositions(grid: List<String>): Map<Int, List<Int>> {
    return grid.indices
        .associateWith { rowIndex -> findRemovableInRow(grid, rowIndex) }
        .filterValues { it.isNotEmpty() }
}

private fun findRemovableInRow(grid: List<String>, rowIndex: Int): List<Int> {
    return grid[rowIndex].indices.filter { colIndex ->
        grid[rowIndex][colIndex] != EMPTY_PLACE && canBeRemoved(grid, rowIndex, colIndex)
    }
}

private fun canBeRemoved(grid: List<String>, row: Int, col: Int): Boolean {
    val adjacentRolls = countAdjacentRolls(grid, row, col)
    return adjacentRolls < 5
}

private fun countAdjacentRolls(grid: List<String>, row: Int, col: Int): Int {
    return (-1..1).flatMap { dRow ->
        (-1..1).mapNotNull { dCol ->
            if (dRow == 0 && dCol == 0) return@mapNotNull null

            val newRow = row + dRow
            val newCol = col + dCol
            if (newRow in grid.indices && newCol in grid[0].indices) {
                if (grid[newRow][newCol] == ROLL_SYMBOL) 1 else 0
            } else 0
        }
    }.sum()
}

private fun applyChanges(grid: List<String>, changes: Map<Int, List<Int>>): List<String> {
    return grid.mapIndexed { index, row ->
        val columnsToRemove = changes[index] ?: emptyList()
        if (columnsToRemove.isEmpty()) {
            row
        } else {
            row.mapIndexed { colIndex, char ->
                if (colIndex in columnsToRemove) EMPTY_PLACE else char
            }.joinToString("")
        }
    }
}
