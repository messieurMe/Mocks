package stubs

import common.BaseStub.baseStub
import model.SubredditResponse
import model.SubredditResponse.SubredditData.SubredditPosts
import model.TimeType
import reddit_repository.retrofit.RedditPostsService
import retrofit2.Call
import statistics.Statistics
import java.net.UnknownHostException

class StatisticsStub : Statistics{
    override fun getMinScore(array: IntArray) = baseStub()

    override fun getMaxScore(array: IntArray) = baseStub()

    override fun getAverageScore(array: IntArray): Double {
        return 0.0
    }

    override var lastResult: Double? = baseStub()
}