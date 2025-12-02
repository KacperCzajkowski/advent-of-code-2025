package dayTwo

class IndexValidator {
    fun isInvalid(string: String): Boolean {
        if (string.length % 2 != 0) {
            return false
        }

        val chunked = string.chunked(string.length / 2)
        return chunked[0] == chunked[1]
    }
}