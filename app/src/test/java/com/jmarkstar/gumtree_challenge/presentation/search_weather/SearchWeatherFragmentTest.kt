package com.jmarkstar.gumtree_challenge.presentation.search_weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jmarkstar.gumtree_challenge.common.BaseTest
import com.jmarkstar.gumtree_challenge.common.TestCoroutineRule
import com.jmarkstar.gumtree_challenge.di.TestModule
import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.GetLastFiveRecentSearchesUseCase
import com.jmarkstar.gumtree_challenge.domain.usecases.weather.GetWeatherByQueryUseCase
import com.jmarkstar.gumtree_challenge.presentation.di.UseCasesModule
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
@UninstallModules(UseCasesModule::class, TestModule::class)
@HiltAndroidTest
class SearchWeatherFragmentTest : BaseTest() {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = TestCoroutineRule()

    @BindValue
    @JvmField
    val getWeatherByQueryUseCase = mockk<GetWeatherByQueryUseCase>()

    @BindValue
    @JvmField
    val getLastFiveRecentSearchesUseCase = mockk<GetLastFiveRecentSearchesUseCase>()

    @BindValue
    @JvmField
    val coroutineDispatcher = coroutineRule.testCoroutineDispatcher

    private val queryMock = "Chatswood, AU"

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `test get weather success`() {
        // TODO: UI TEST
    }
}
