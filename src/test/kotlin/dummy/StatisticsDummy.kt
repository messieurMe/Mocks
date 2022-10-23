package dummy

import common.BaseStub.baseStub
import statistics.Statistics

class StatisticsDummy : Statistics {
    override fun getMinScore(array: IntArray) = baseStub()

    override fun getMaxScore(array: IntArray) = baseStub()

    override fun getAverageScore(array: IntArray) = baseStub()

    override var lastResult: Double? = baseStub()
}
