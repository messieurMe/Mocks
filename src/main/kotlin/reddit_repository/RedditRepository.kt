package reddit_repository

import kotlinx.coroutines.runBlocking
import model.SubredditResponse.SubredditData.SubredditPosts
import model.TimeType
import reddit_repository.retrofit.RedditPostsService
import retrofit2.awaitResponse

interface RedditRepository {
    var timeType: TimeType
    var subreddit: String

    fun nextPage(limit: Int): List<SubredditPosts>?

}

class RedditRepositoryImpl(
    private val redditPostsService: RedditPostsService, initialSubReddit: String
) : RedditRepository {

    override var subreddit: String = initialSubReddit
        set(value) {
            wipeData()
            field = value
        }

    override var timeType: TimeType = TimeType.YEAR
        set(value) {
            wipeData()
            field = value
        }

    private var lastPage: String? = null


    override fun nextPage(limit: Int): List<SubredditPosts>? = runBlocking {
        try {
            val request = redditPostsService.listPosts(subreddit, limit, timeType.string, lastPage)
            val result = request.awaitResponse().body()?.data
            lastPage = result?.after ?: lastPage

            result?.children?.map { it.data }
        } catch (e: Exception) {
            println(e::class.java.name)
            null
        }
    }

    private fun wipeData() {
        lastPage = ""
    }
}