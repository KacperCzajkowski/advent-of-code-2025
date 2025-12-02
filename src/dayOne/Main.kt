package dayOne

import java.io.File


val TASK_2 = true

fun main() {
    val dial = Dial()
    File("src/dayOne/input.txt")
        .forEachLine {
            val direction = it.first()
            val units = it.drop(1).toInt()
            when (TASK_2) {
                true -> repeat(units) { dial.processStep(direction, 1) }
                false -> dial.processStep(direction, units)
            }
        }
    println("Zeros: ${dial.getCountedZeros()}")
}
