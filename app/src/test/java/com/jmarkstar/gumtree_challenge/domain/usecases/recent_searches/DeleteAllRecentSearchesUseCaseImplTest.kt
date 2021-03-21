package com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches

import com.jmarkstar.gumtree_challenge.common.BaseTest
import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.repositories.RecentSearchRepository
import com.jmarkstar.gumtree_challenge.repositories.exceptions.DatabaseException
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DeleteAllRecentSearchesUseCaseImplTest : BaseTest() {

    private val recentSearchRepository = mockk<RecentSearchRepository>()

    private lateinit var deleteAllRecentSearchesUseCase: DeleteAllRecentSearchesUseCase

    @Before
    fun setUp() {
        deleteAllRecentSearchesUseCase = DeleteAllRecentSearchesUseCaseImpl(recentSearchRepository)
    }

    @Test
    fun `test delete all recent searches use case  success`()  = runBlockingTest {
        // Given
        val fakeResult = ResultOf.Success(true)
        coEvery { recentSearchRepository.deleteAllRecentSearches() } returns fakeResult

        // When
        val getDeleteAllSearchesResult = deleteAllRecentSearchesUseCase.invoke()

        // Then
        assert(getDeleteAllSearchesResult is ResultOf.Success)
        assert((getDeleteAllSearchesResult as ResultOf.Success).value)
    }

    @Test
    fun `test delete all recent searches use case  failure`()  = runBlockingTest {
        // Given
        val databaseExceptionMock = mockk<DatabaseException>()
        val fakeResult = ResultOf.Failure(databaseExceptionMock)
        coEvery { recentSearchRepository.deleteAllRecentSearches() } returns fakeResult

        // When
        val getDeleteAllSearchesResult = deleteAllRecentSearchesUseCase.invoke()

        // Then
        assert(getDeleteAllSearchesResult is ResultOf.Failure)
        val exception = (getDeleteAllSearchesResult as ResultOf.Failure).throwable
        assert(exception is DatabaseException)
    }
}
