package day_6

import java.io.File

fun main() {
//    val lines = File("src/day_6/test.txt").readLines()
    val lines = File("src/day_6/input.txt").readLines()
    val grid = lines.map { line ->
        val splitted = line.split(" ")
        splitted.filter { it.isNotEmpty() }.map { it.trim() }
    }
    var sum: Long = 0
    repeat(grid[0].size) { i ->
        val operation = grid.last()[i]
        var startValue: Long = if (operation == "*") 1 else 0
        for (j in 0..<grid.size - 1) {
            val value = grid[j][i].toLong()
            if (operation == "*") {
                startValue *= value
            } else if (operation == "+") {
                startValue += value
            }
        }
        println("I: $i, startValue: $startValue")
        sum += startValue
    }

    println("Result: $sum")
}