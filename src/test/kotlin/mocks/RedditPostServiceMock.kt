package mocks

import model.SubredditResponse
import reddit_repository.retrofit.RedditPostsService
import retrofit2.Call
import java.net.UnknownHostException

class RedditPostServiceThrowingExceptionMock : RedditPostsService {

    override fun listPosts(subreddit: String, limit: Int, lastTime: String, after: String?): Call<SubredditResponse> {
        throw UnknownHostException("Unknown host")
    }
}