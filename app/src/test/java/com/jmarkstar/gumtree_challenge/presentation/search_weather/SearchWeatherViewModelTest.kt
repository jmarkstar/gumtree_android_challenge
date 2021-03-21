package com.jmarkstar.gumtree_challenge.presentation.search_weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jmarkstar.gumtree_challenge.common.BaseTest
import com.jmarkstar.gumtree_challenge.common.TestCoroutineRule
import com.jmarkstar.gumtree_challenge.common.coroutines.DispatcherProvider
import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.models.RecentSearchModel
import com.jmarkstar.gumtree_challenge.domain.models.WeatherModel
import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.GetLastFiveRecentSearchesUseCase
import com.jmarkstar.gumtree_challenge.domain.usecases.weather.GetWeatherByQueryUseCase
import com.jmarkstar.gumtree_challenge.fakeLastFiveSearchModels
import com.jmarkstar.gumtree_challenge.fakeWeather
import com.jmarkstar.gumtree_challenge.presentation.common.Resource
import com.jmarkstar.gumtree_challenge.repositories.exceptions.DatabaseException
import com.jmarkstar.gumtree_challenge.repositories.exceptions.NetworkException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchWeatherViewModelTest : BaseTest() {

    @get:Rule
    val coroutineRule = TestCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcherProvider = mockk<DispatcherProvider>()

    private val getWeatherByQueryUseCase = mockk<GetWeatherByQueryUseCase>()

    private val getLastFiveRecentSearchesUseCase = mockk<GetLastFiveRecentSearchesUseCase>()

    private lateinit var searchWeatherViewModel: SearchWeatherViewModel

    private val queryMock = "query mock"

    @Before
    fun setUp() {

        every { dispatcherProvider.IO } returns coroutineRule.testCoroutineDispatcher

        // I can initiate my view model in here because is not loading anything in  `init{ }`
        searchWeatherViewModel = SearchWeatherViewModel(
            getWeatherByQueryUseCase,
            getLastFiveRecentSearchesUseCase,
            dispatcherProvider
        )
    }

    @Test
    fun `test search view model get weather success`() {
        // Given
        val mockResult = ResultOf.Success(fakeWeather)

        coEvery { getWeatherByQueryUseCase.invoke(queryMock) } returns mockResult

        // When
        coroutineRule.pauseDispatcher()
        searchWeatherViewModel.searchWeather(queryMock)

        // Then
        val weatherLiveData = searchWeatherViewModel.weather
        assert(weatherLiveData.value is Resource.Loading)

        coroutineRule.resumeDispatcher()

        coVerify { getWeatherByQueryUseCase.invoke(queryMock) }

        assert(weatherLiveData.value is Resource.Success<WeatherModel>)
        val successResource = weatherLiveData.value as Resource.Success<WeatherModel>
        assertEquals(fakeWeather.temperature, successResource.data?.temperature)
        // TODO: Compare all fields
    }

    @Test
    fun `test search view model get weather failure`() {
        // Given
        val errorMessageMock = "network error message mock"
        val exceptionMock = mockk<NetworkException>()
        every { exceptionMock.message } returns errorMessageMock

        val mockResult = ResultOf.Failure(exceptionMock)

        coEvery { getWeatherByQueryUseCase.invoke(queryMock) } returns mockResult

        // When
        coroutineRule.pauseDispatcher()
        searchWeatherViewModel.searchWeather(queryMock)

        // Then
        val weatherLiveData = searchWeatherViewModel.weather
        assert(weatherLiveData.value is Resource.Loading)

        coroutineRule.resumeDispatcher()

        coVerify { getWeatherByQueryUseCase.invoke(queryMock) }

        assert(weatherLiveData.value is Resource.Error<WeatherModel>)
        val failureResource = weatherLiveData.value as Resource.Error<WeatherModel>
        assertEquals(errorMessageMock, failureResource.message)
    }

    @Test
    fun `test search view model get last searches success`() {
        // Given
        val mockResult = ResultOf.Success(fakeLastFiveSearchModels)

        coEvery { getLastFiveRecentSearchesUseCase.invoke() } returns mockResult

        // When
        coroutineRule.pauseDispatcher()
        searchWeatherViewModel.loadFiveLastSearches()

        // Then
        val lastSearchesLiveData = searchWeatherViewModel.lastSearches
        assert(lastSearchesLiveData.value is Resource.Loading)

        coroutineRule.resumeDispatcher()

        coVerify { getLastFiveRecentSearchesUseCase.invoke() }

        assert(lastSearchesLiveData.value is Resource.Success<List<RecentSearchModel>>)
        val successResource = lastSearchesLiveData.value as Resource.Success<List<RecentSearchModel>>
        assertEquals(fakeLastFiveSearchModels.size, successResource.data?.size)
        // TODO: compare more fields
    }

    @Test
    fun `test search view model get last searches failure`() {
        // Given
        val mockResult = ResultOf.Failure(DatabaseException())

        coEvery { getLastFiveRecentSearchesUseCase.invoke() } returns mockResult

        // When
        coroutineRule.pauseDispatcher()
        searchWeatherViewModel.loadFiveLastSearches()

        // Then
        val lastSearchesLiveData = searchWeatherViewModel.lastSearches
        assert(lastSearchesLiveData.value is Resource.Loading)

        coroutineRule.resumeDispatcher()

        coVerify { getLastFiveRecentSearchesUseCase.invoke() }

        assert(lastSearchesLiveData.value is Resource.Error)
    }
}
