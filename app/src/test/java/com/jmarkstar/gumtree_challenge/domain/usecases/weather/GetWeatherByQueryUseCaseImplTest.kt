package com.jmarkstar.gumtree_challenge.domain.usecases.weather

import com.jmarkstar.gumtree_challenge.common.BaseTest
import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.repositories.WeatherRepository
import com.jmarkstar.gumtree_challenge.fakeWeather
import com.jmarkstar.gumtree_challenge.repositories.exceptions.DatabaseException
import com.jmarkstar.gumtree_challenge.repositories.exceptions.NetworkException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetWeatherByQueryUseCaseImplTest : BaseTest() {

    private val weatherRepository = mockk<WeatherRepository>()

    private lateinit var getWeatherByQueryUseCase: GetWeatherByQueryUseCase

    private val queryFake = "query fake"

    @Before
    fun setUp() {
        getWeatherByQueryUseCase = GetWeatherByQueryUseCaseImpl(weatherRepository)
    }

    @Test
    fun `test get weather by query use case success`() = runBlockingTest {
        // Given
        val fakeResult = ResultOf.Success(fakeWeather)
        coEvery { weatherRepository.getWeatherBy(queryFake) } returns fakeResult

        // When
        val useCaseResult = getWeatherByQueryUseCase.invoke(queryFake)

        // Then
        assert(useCaseResult is ResultOf.Success)
        val resultWeather = (useCaseResult as ResultOf.Success).value
        assertEquals(fakeWeather.temperature, resultWeather.temperature)
    }

    @Test
    fun `test get weather by query use case failure`() = runBlockingTest {
        // Given
        val networkExceptionMock = NetworkException(404, "city not found")
        val fakeResult = ResultOf.Failure(networkExceptionMock)
        coEvery { weatherRepository.getWeatherBy(queryFake) } returns fakeResult

        // When
        val useCaseResult = getWeatherByQueryUseCase.invoke(queryFake)

        // Then
        assert(useCaseResult is ResultOf.Failure)
        val userCaseException = ((useCaseResult as ResultOf.Failure).throwable) as NetworkException
        assertEquals(networkExceptionMock.code, userCaseException.code)
        assertEquals(networkExceptionMock.message, userCaseException.message)
    }
}
