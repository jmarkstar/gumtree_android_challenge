package com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches

import com.jmarkstar.gumtree_challenge.common.BaseTest
import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.repositories.RecentSearchRepository
import com.jmarkstar.gumtree_challenge.fakeRecentSearches
import com.jmarkstar.gumtree_challenge.repositories.entities.toModels
import com.jmarkstar.gumtree_challenge.repositories.exceptions.DatabaseException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetLastFiveRecentSearchesUseCaseImplTest : BaseTest() {

    private val recentSearchRepository = mockk<RecentSearchRepository>()

    private lateinit var getLastFiveRecentSearchesUseCase: GetLastFiveRecentSearchesUseCase

    @Before
    fun setUp() {
        getLastFiveRecentSearchesUseCase = GetLastFiveRecentSearchesUseCaseImpl(recentSearchRepository)
    }

    @Test
    fun `test get last five recent searches use case success`() = runBlockingTest {
        // Given
        val models = fakeRecentSearches.toModels()
        val lastIndex = fakeRecentSearches.size - 1
        val fakeResult = ResultOf.Success(models.subList(lastIndex-5, lastIndex))
        coEvery { recentSearchRepository.getLastFiveRecentSearches() } returns fakeResult

        // When
        val useCaseResult = getLastFiveRecentSearchesUseCase.invoke()

        // Then
        assert(useCaseResult is ResultOf.Success)
        assertEquals(5, (useCaseResult as ResultOf.Success).value.size)
    }

    @Test
    fun `test get last five recent searches use case failure`() = runBlockingTest {
        // Given
        val databaseExceptionMock = mockk<DatabaseException>()
        val fakeResult = ResultOf.Failure(databaseExceptionMock)
        coEvery { recentSearchRepository.getLastFiveRecentSearches() } returns fakeResult

        // When
        val useCaseResult = getLastFiveRecentSearchesUseCase.invoke()

        // Then
        assert(useCaseResult is ResultOf.Failure)
    }
}
