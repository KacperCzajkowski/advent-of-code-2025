package day_3

class MaxBatteryJoltageExtractor {
    fun twoBatteriesExtract(bank: String): Int {
        var firstBiggest = bank[0]
        var secondBiggest = bank[1]

        for (i in 2..<bank.length) {
            if (toInt(firstBiggest, bank[i]) < toInt(secondBiggest, bank[i])) {
                firstBiggest = secondBiggest
                secondBiggest = bank[i]

                continue
            }

            if (toInt(firstBiggest, secondBiggest) < toInt(firstBiggest, bank[i])) {
                secondBiggest = bank[i]
            }
        }

        return toInt(firstBiggest, secondBiggest)
    }

    fun twelveBatteriesExtract(bank: String): Long {
        var result = StringBuilder()
        var lastFoundOnIndex = 0

        repeat(12) { i ->
            val startIndex = if (i == 0) 0 else lastFoundOnIndex + 1
            var currentBiggest = bank[startIndex].digitToInt()
            lastFoundOnIndex = startIndex
            val max = bank.length - 12 + i
            for (j in startIndex + 1..max) {
                val possibleValue = bank[j].digitToInt()
                if (currentBiggest < possibleValue) {
                    currentBiggest = possibleValue
                    lastFoundOnIndex = j
                }
            }
            result = result.append(currentBiggest)
        }

        return result.toString().toLong()
    }

    private fun toInt(first: Char, second: Char): Int {
        return first.plus(second.toString()).toInt()
    }
}