package day_5

import java.io.File
import kotlin.math.max
import kotlin.math.min

const val TASK_2 = true

fun main() {
    val readLines = File("src/day_5/input.txt").readLines()

    val ranges = ArrayList<Pair<Long, Long>>()
    val numbers = ArrayList<Long>()
    var wasBreak = false
    readLines.forEach {
        if (it.isEmpty()) {
            wasBreak = true
        } else if (wasBreak) {
            numbers.add(it.toLong())
        } else {
            val tmp = it.split("-")
            ranges.add(Pair(tmp[0].toLong(), tmp[1].toLong()))
        }
    }
    ranges.sortWith { o1, o2 -> if (o1.first > o2.first) 1 else -1 }
    numbers.sort()

    var freshnessCounter: Long = 0

    if (!TASK_2) {
        var currentRangeIndex = 0
        numbers.forEach { number ->
            var currentRange = ranges[currentRangeIndex]
            while (number > currentRange.second && currentRangeIndex + 1 < ranges.size) {
                currentRange = ranges[++currentRangeIndex]
            }
            if (number >= currentRange.first && number <= currentRange.second) {
                freshnessCounter++
            }
        }
    } else {
        var mergedRanges = ranges
        var rangesCountBefore: Int
        do {
            rangesCountBefore = mergedRanges.size
            mergedRanges = mergeRanges(mergedRanges)
        } while ((rangesCountBefore - mergedRanges.size) != 0)

        freshnessCounter = mergedRanges.sumOf { it.second - it.first + 1 }
    }

    println("Result: $freshnessCounter")
}

private fun mergeRanges(ranges: ArrayList<Pair<Long, Long>>): ArrayList<Pair<Long, Long>> {
    val mergedRanges = ArrayList<Pair<Long, Long>>()
    var i = 0
    while (i < ranges.size - 1) {
        val first = ranges[i]
        val second = ranges[i + 1]
        if (first.second >= second.first) {
            mergedRanges.add(
                Pair(
                    min(first.first, second.first),
                    max(first.second, second.second)
                )

            )
            i += 2
        } else {
            mergedRanges.add(first)
            i++
        }
    }
    if (i + 1 == ranges.size) {
        mergedRanges.add(ranges.last())
    }

    return mergedRanges
}