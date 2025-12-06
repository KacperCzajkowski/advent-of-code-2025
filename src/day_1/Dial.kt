package day_1

class Dial(private var position: Int = 50, private var zerosCounter: Int = 0) {
    fun processStep(direction: Char, units: Int) {
        if (direction == 'L') {
            turnLeft(units)
        } else if (direction == 'R') {
            turnRight(units)
        }
    }

    fun getCountedZeros(): Int {
        return zerosCounter
    }

    private fun turnLeft(units: Int) {
        val actualUnit = units % 100
        if (position > actualUnit) {
            position -= actualUnit
        } else {
            position = (100 - (actualUnit - position)) % 100
        }

        if (position == 0) {
            zerosCounter++
        }
    }

    private fun turnRight(units: Int) {
        val actualUnits = units % 100
        position = (position + actualUnits) % 100


        if (position == 0) {
            zerosCounter++
        }
    }
}