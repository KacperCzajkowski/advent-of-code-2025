package dayTwo

import java.io.File

val TASK_2 = true

fun main() {
    var sum: Long = 0
    File("src/dayTwo/input.txt").forEachLine { line ->
        line.split(",").forEach {
            val splitted = it.split("-")
            val begin = splitted[0].toLong()
            val end = splitted[1].toLong()
            val validator = IndexValidator()
            for (i in begin..end) {
                if (resolveValidation(validator, i.toString())) {
                    sum += i
                }
            }
        }
    }
    println("Value: $sum")
}

fun resolveValidation(validator: IndexValidator, string: String): Boolean {
    if (TASK_2) {
        return validator.isInvalidPart2(string)
    }

    return validator.isInvalid(string)
}