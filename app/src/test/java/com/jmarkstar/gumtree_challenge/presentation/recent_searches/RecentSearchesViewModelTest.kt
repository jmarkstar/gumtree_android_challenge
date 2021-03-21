package com.jmarkstar.gumtree_challenge.presentation.recent_searches

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jmarkstar.gumtree_challenge.common.BaseTest
import com.jmarkstar.gumtree_challenge.common.TestCoroutineRule
import com.jmarkstar.gumtree_challenge.common.coroutines.DispatcherProvider
import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.models.RecentSearchModel
import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.DeleteAllRecentSearchesUseCase
import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.GetAllRecentSearchesUseCase
import com.jmarkstar.gumtree_challenge.fakeRecentSearches
import com.jmarkstar.gumtree_challenge.presentation.common.Resource
import com.jmarkstar.gumtree_challenge.repositories.entities.toModels
import com.jmarkstar.gumtree_challenge.repositories.exceptions.DatabaseException
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
class RecentSearchesViewModelTest : BaseTest() {

    @get:Rule
    val coroutineRule = TestCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcherProvider = mockk<DispatcherProvider>()

    private val getAllRecentSearchesUseCase = mockk<GetAllRecentSearchesUseCase>()

    private val deleteAllRecentSearchesUseCase = mockk<DeleteAllRecentSearchesUseCase>()

    private lateinit var recentSearchesViewModel: RecentSearchesViewModel

    @Before
    fun setUp() {

        every { dispatcherProvider.IO } returns coroutineRule.testCoroutineDispatcher

        recentSearchesViewModel = RecentSearchesViewModel(
            getAllRecentSearchesUseCase,
            deleteAllRecentSearchesUseCase,
            dispatcherProvider
        )
    }

    @Test
    fun `test recent searches view model get all success`() {
        // Given
        val fakeSearchModels = fakeRecentSearches.toModels()
        val mockResult = ResultOf.Success(fakeSearchModels)

        coEvery { getAllRecentSearchesUseCase.invoke() } returns mockResult

        // When
        coroutineRule.pauseDispatcher()
        recentSearchesViewModel.loadAllSearches()

        // Then
        val recentSearchesLiveData = recentSearchesViewModel.recentSearches
        assert(recentSearchesLiveData.value is Resource.Loading)

        coroutineRule.resumeDispatcher()

        coVerify { getAllRecentSearchesUseCase.invoke() }

        assert(recentSearchesLiveData.value is Resource.Success<List<RecentSearchModel>>)
        val successResource = recentSearchesLiveData.value as Resource.Success<List<RecentSearchModel>>
        assertEquals(fakeSearchModels.size, successResource.data?.size)
    }

    @Test
    fun `test recent searches view model get all failure`() {
        // Given
        val mockResult = ResultOf.Failure(DatabaseException())

        coEvery { getAllRecentSearchesUseCase.invoke() } returns mockResult

        // When
        coroutineRule.pauseDispatcher()
        recentSearchesViewModel.loadAllSearches()

        // Then
        val recentSearchesLiveData = recentSearchesViewModel.recentSearches
        assert(recentSearchesLiveData.value is Resource.Loading)

        coroutineRule.resumeDispatcher()

        coVerify { getAllRecentSearchesUseCase.invoke() }

        assert(recentSearchesLiveData.value is Resource.Error)
    }

    @Test
    fun `test recent searches view model delete all success`() {
        // Given
        val mockResult = ResultOf.Success(true)

        coEvery { deleteAllRecentSearchesUseCase.invoke() } returns mockResult

        // When
        coroutineRule.pauseDispatcher()
        recentSearchesViewModel.deleteAll()

        // Then
        val deleteSearchesLiveData = recentSearchesViewModel.deleteSearches
        assert(deleteSearchesLiveData.value is Resource.Loading)

        coroutineRule.resumeDispatcher()

        coVerify { deleteAllRecentSearchesUseCase.invoke() }

        assert(deleteSearchesLiveData.value is Resource.Success)
    }

    @Test
    fun `test recent searches view model delete all failure`() {
        // Given
        val mockResult = ResultOf.Failure(DatabaseException())

        coEvery { deleteAllRecentSearchesUseCase.invoke() } returns mockResult

        // When
        coroutineRule.pauseDispatcher()
        recentSearchesViewModel.deleteAll()

        // Then
        val deleteSearchesLiveData = recentSearchesViewModel.deleteSearches
        assert(deleteSearchesLiveData.value is Resource.Loading)

        coroutineRule.resumeDispatcher()

        coVerify { deleteAllRecentSearchesUseCase.invoke() }

        assert(deleteSearchesLiveData.value is Resource.Error)
    }
}
