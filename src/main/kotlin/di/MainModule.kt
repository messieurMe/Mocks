package di

import reddit_repository.RedditRepository
import statistics.Statistics

interface MainModule {
    val redditRepository: RedditRepository

    val statistics: Statistics
}

fun MainModule(baseUrl: String, initalSubreddit: String) = object : MainModule {

    val dependencies: MainDependencies
        get() = MainDependencies(baseUrl, initalSubreddit)

    override val redditRepository: RedditRepository
        get() = dependencies.redditRepository

    override val statistics: Statistics
        get() = dependencies.statistics
}