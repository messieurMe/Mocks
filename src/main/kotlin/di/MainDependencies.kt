package di

import com.google.gson.Gson
import reddit_repository.RedditRepository
import reddit_repository.RedditRepositoryImpl
import reddit_repository.retrofit.RedditPostsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import statistics.Statistics
import statistics.StatisticsImpl

interface MainDependencies {

    val redditRepository: RedditRepository

    val redditPostsService: RedditPostsService

    val statistics: Statistics
}

fun MainDependencies(
    baseUrl: String, initialSubreddit: String
) = object : MainDependencies {

    private val gson: Gson = Gson()

    private val converterFactory: GsonConverterFactory = GsonConverterFactory.create(gson)

    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(converterFactory).build()

    override val redditPostsService: RedditPostsService = retrofit.create(RedditPostsService::class.java)

    override val redditRepository: RedditRepository = RedditRepositoryImpl(redditPostsService, initialSubreddit)

    override val statistics: Statistics = StatisticsImpl()
}