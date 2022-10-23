package statistics

interface Statistics {
    fun getMinScore(array: IntArray): Double

    fun getMaxScore(array: IntArray): Double

    fun getAverageScore(array: IntArray): Double

    var lastResult: Double?
}

open class StatisticsImpl : Statistics {
    override fun getMinScore(array: IntArray) = functionProxy(array) { array.min().toDouble() }

    override fun getMaxScore(array: IntArray) = functionProxy(array) { array.max().toDouble() }

    override fun getAverageScore(array: IntArray) = functionProxy(array) { array.average() }

    private fun <T> functionProxy(input: T, function: (T) -> Double): Double {
        val newResult = function(input)

        lastResult = newResult

        return newResult
    }

    override var lastResult: Double? = null
}