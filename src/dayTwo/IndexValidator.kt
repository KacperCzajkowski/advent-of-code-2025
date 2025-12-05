package dayTwo

class IndexValidator {
    fun isInvalid(string: String): Boolean {
        if (string.length % 2 != 0) {
            return false
        }

        val chunked = string.chunked(string.length / 2)
        return chunked[0] == chunked[1]
    }

    fun isInvalidPart2(string: String): Boolean {
        val tmp = string
            .groupingBy { it }
            .eachCount()

        if (tmp.any { it.value < 2 }) {
            return false
        }

        if (tmp.values.distinct().size == 1) {
            return string
                .chunked(tmp.keys.count())
                .groupingBy { it }
                .eachCount()
                .keys
                .count() == 1

        }

        return isInvalid(string)
    }
}