import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.github.tomakehurst.wiremock.junit.WireMockRule
import model.SubredditResponse.SubredditData.SubredditPosts
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import realisations.MainModuleTypesImpl.mockedModule
import realisations.MainModuleTypesImpl.mockedModuleWithErrorOnRequest
import reddit_repository.RedditRepositoryImpl
import reddit_repository.retrofit.RedditPostsService
import java.io.FileReader
import java.net.UnknownHostException

@RunWith(MockitoJUnitRunner::class)
class MainModuleTest {

    @JvmField
    @Rule
    val wireMock = WireMockRule(wireMockConfig().port(8080))

    @Test
    fun `check first request is correct`() {
        val mock = FileReader("src/test/resources/mocks/initial5.json").readLines().joinToString("")
        wireMock.stubFor(
            WireMock.get(WireMock.anyUrl())
                .willReturn(
                    WireMock.ok().withBody(mock)
                )
        )
        wireMock.start()

        val response = mockedModule.redditRepository.nextPage(5)
        assert(response!!.first().title == "From mock")
    }

    @Test
    fun `check no internet`() {
        val result: List<SubredditPosts>? = mockedModuleWithErrorOnRequest.redditRepository.nextPage(10)
        assert(result == null)
    }

    @Test
    fun `check no internet (via mock)`() {
        val redditPostServiceMock = mock(RedditPostsService::class.java)
        `when`(redditPostServiceMock.listPosts(anyString(), anyInt(), anyString(), anyString()))
            .thenThrow(UnknownHostException::class.java)

        val noInternetRepository = RedditRepositoryImpl(redditPostServiceMock, "--")

        assert(noInternetRepository.nextPage(10) == null)
    }

}