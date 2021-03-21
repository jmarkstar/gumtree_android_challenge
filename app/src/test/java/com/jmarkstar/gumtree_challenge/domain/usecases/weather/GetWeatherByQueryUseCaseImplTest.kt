package com.jmarkstar.gumtree_challenge.domain.usecases.weather

import com.jmarkstar.gumtree_challenge.common.BaseTest
import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.repositories.WeatherRepository
import com.jmarkstar.gumtree_challenge.fakeWeather
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

    @Before
    fun setUp() {
        getWeatherByQueryUseCase = GetWeatherByQueryUseCaseImpl(weatherRepository)
    }

    @Test
    fun `test get weather by query use case success`() = runBlockingTest {
        // Given
        val queryFake = "query fake"
        val fakeResult = ResultOf.Success(fakeWeather)
        coEvery { weatherRepository.getWeatherBy(queryFake) } returns fakeResult

        // When
        val useCaseResult = getWeatherByQueryUseCase.invoke(queryFake)

        // Then
        assert(useCaseResult is ResultOf.Success)
        val resultWeather = (useCaseResult as ResultOf.Success).value
        assertEquals(fakeWeather.temperature, resultWeather.temperature)
    }
}
