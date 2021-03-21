package com.jmarkstar.gumtree_challenge.repositories

import com.google.gson.Gson
import com.jmarkstar.gumtree_challenge.common.BaseTest
import com.jmarkstar.gumtree_challenge.common.UnitTestUtils
import com.jmarkstar.gumtree_challenge.common.util.NetworkState
import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.repositories.WeatherRepository
import com.jmarkstar.gumtree_challenge.repositories.exceptions.NetworkException
import com.jmarkstar.gumtree_challenge.repositories.exceptions.NotInternetException
import com.jmarkstar.gumtree_challenge.repositories.local.daos.RecentSearchDao
import com.jmarkstar.gumtree_challenge.repositories.network.WeatherService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class WeatherRepositoryImplTest : BaseTest() {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val mockWebServer = MockWebServer()

    @Inject
    lateinit var gson: Gson

    @Inject
    lateinit var networkState: NetworkState

    @Inject
    lateinit var recentSearchDao: RecentSearchDao

    @Inject
    lateinit var weatherService: WeatherService

    private lateinit var weatherRepository: WeatherRepository

    private val queryMock = "city mock, PE"

    @Before
    fun setUp() {
        hiltRule.inject()
        mockWebServer.start(UnitTestUtils.MOCK_SERVER_PORT)

        weatherRepository = WeatherRepositoryImpl(
            recentSearchDao,
            weatherService,
            networkState,
            gson
        )
    }

    @After
    fun stopMockServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test weather repository get weather when is success`() = runBlocking {
        // Given
        networkState.isConnected = true
        mockWebServer.enqueue(UnitTestUtils.mockJsonResponse(200, "get_weather_response_success.json"))
        recentSearchDao.deleteAll()

        // When
        val resultOf = weatherRepository.getWeatherBy(queryMock)

        // Then
        assert(resultOf is ResultOf.Success)
        val movieModel = (resultOf as ResultOf.Success).value
        assertEquals("Rain", movieModel.name)
        assertEquals("moderate rain", movieModel.description)
        assertEquals("https://openweathermap.org/img/wn/10n@2x.png", movieModel.iconUrl)
        assertEquals(20, movieModel.temperature)
        assertEquals(23, movieModel.feelsLike)
        assertEquals(98, movieModel.humidity)

        val newRecentSearches = recentSearchDao.getAll()
        assertEquals(1, newRecentSearches.size)
        assertEquals(queryMock, newRecentSearches[0].query)
    }

    @Test
    fun `test weather repository get weather when there is not internet connection`() = runBlocking {
        // Given
        networkState.isConnected = false

        // When
        val resultOf = weatherRepository.getWeatherBy(queryMock)

        // Then
        assert(resultOf is ResultOf.Failure)
        val failureResult = resultOf as ResultOf.Failure

        assert(failureResult.throwable is NotInternetException)
    }

    @Test
    fun `test weather repository get weather when query is not found`() = runBlocking {
        // Given
        networkState.isConnected = true
        mockWebServer.enqueue(UnitTestUtils.mockJsonResponse(404, "get_weather_response_not_found.json"))

        // When
        val resultOf = weatherRepository.getWeatherBy(queryMock)

        // Then
        assert(resultOf is ResultOf.Failure)
        val failureResult = resultOf as ResultOf.Failure

        assert(failureResult.throwable is NetworkException)
        val exception = failureResult.throwable as NetworkException

        assertEquals(404, exception.code)
        assertEquals("city not found", exception.message)
    }

    @Test
    fun `test weather repository get weather when user does not have access`() = runBlocking {
        // Given
        networkState.isConnected = true
        mockWebServer.enqueue(UnitTestUtils.mockJsonResponse(401, "get_weather_response_not_allowed.json"))

        // When
        val resultOf = weatherRepository.getWeatherBy(queryMock)

        // Then
        assert(resultOf is ResultOf.Failure)
        val failureResult = resultOf as ResultOf.Failure

        assert(failureResult.throwable is NetworkException)
        val exception = failureResult.throwable as NetworkException

        assertEquals(401, exception.code)
        assertEquals("Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.", exception.message)
    }
}
