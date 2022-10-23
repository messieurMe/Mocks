package realisations

import di.MainDependencies
import di.MainModule
import dummy.StatisticsDummy
import mocks.RedditPostServiceThrowingExceptionMock
import org.mockito.Mockito.mock
import reddit_repository.RedditRepository
import reddit_repository.RedditRepositoryImpl
import reddit_repository.retrofit.RedditPostsService
import statistics.Statistics
import stubs.StatisticsStub

object MainModuleTypesImpl {

    val mockedModule
        get() = MainModule("http://localhost:8080/", "puppies")


    private val testDependencies: MainDependencies = object : MainDependencies {
        override val redditPostsService: RedditPostsService = RedditPostServiceThrowingExceptionMock()

        override val redditRepository: RedditRepository = RedditRepositoryImpl(redditPostsService, "--")

        override val statistics: Statistics by lazy { StatisticsDummy() }
    }

    val mockedModuleWithErrorOnRequest: MainModule
        get() = object : MainModule, MainDependencies by testDependencies {}
}