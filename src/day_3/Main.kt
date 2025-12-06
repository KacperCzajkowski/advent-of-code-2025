package day_3

import java.io.File

const val PART_2 = true

fun main() {
    var sum: Long = 0
    val extractor = MaxBatteryJoltageExtractor()
    File("src/dayThree/input.txt")
        .forEachLine {
            val extract = resolve(extractor, it)
            println("Found: $extract, whole: $it")
            sum += extract
        }
    println("Value: $sum, Expected: 166345822896410, equal ${sum == 166345822896410}")
}

fun resolve(extractor: MaxBatteryJoltageExtractor, string: String): Long {
    if (PART_2) {
        return extractor.twelveBatteriesExtract(string)
    }

    return extractor.twoBatteriesExtract(string).toLong()
}
