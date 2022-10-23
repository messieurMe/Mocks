package di

import reddit_repository.RedditRepository
import statistics.Statistics

interface MainModule {
    val redditRepository: RedditRepository

    val statistics: Statistics
}

fun MainModule(baseUrl: String, initalSubreddit: String): MainModule {
    val dependencies = MainDependencies(baseUrl, initalSubreddit)

    return object : MainModule {

        override val redditRepository: RedditRepository = dependencies.redditRepository

        override val statistics: Statistics = dependencies.statistics
    }
}
