package reddit_repository.retrofit

import model.SubredditResponse
import model.SubredditResponse.SubredditData.SubredditPosts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RedditPostsService {

    @GET("r/{subreddit}/top.json")
    fun listPosts(
        @Path("subreddit") subreddit: String,
        @Query("limit") limit: Int,
        @Query("t") lastTime: String,
        @Query("after") after: String?
    ): Call<SubredditResponse>
}


//public interface GitHubService {
//  @GET("users/{user}/repos")
//  Call<List<Repo>> listRepos(@Path("user") String user);
//}
