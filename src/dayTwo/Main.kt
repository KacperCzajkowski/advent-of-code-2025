package dayTwo

import java.io.File

fun main() {
    var sum: Long = 0
    File("src/dayTwo/input.txt").forEachLine { line ->
        line.split(",").forEach {
            val splitted = it.split("-")
            val begin = splitted[0].toLong()
            val end = splitted[1].toLong()
            val validator = IndexValidator()
            for (i in begin..end) {
                if (validator.isInvalid(i.toString())) {
                    println(i)
                    sum += i
                }
            }
        }
    }
    println("Value: $sum")
}