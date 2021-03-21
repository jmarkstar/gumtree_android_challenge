package com.jmarkstar.gumtree_challenge.repositories

import com.jmarkstar.gumtree_challenge.common.BaseTest
import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.repositories.RecentSearchRepository
import com.jmarkstar.gumtree_challenge.fakeRecentSearches
import com.jmarkstar.gumtree_challenge.repositories.local.daos.RecentSearchDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class RecentSearchRepositoryImplTest : BaseTest() {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var recentSearchDao: RecentSearchDao

    private lateinit var recentSearchRepository: RecentSearchRepository

    @Before
    fun setUp() {
        hiltRule.inject()

        recentSearchRepository = RecentSearchRepositoryImpl(recentSearchDao)
    }

    @Test
    fun `test recent search repository get all`() = runBlocking {
        // Given
        cleanAndInsertFakeData()

        // When
        val resultOf = recentSearchRepository.getAllRecentSearches()

        // Then
        assert(resultOf is ResultOf.Success)
        val models = (resultOf as ResultOf.Success).value
        assertEquals(fakeRecentSearches.size, models.size)

        // comparing first item
        assertEquals(fakeRecentSearches[0].id, models[0].id)
        assertEquals(fakeRecentSearches[0].query, models[0].query)
        assertEquals(fakeRecentSearches[0].datetime, models[0].datetime)
    }

    @Test
    fun `test recent search repository get last five`() = runBlocking {
        // Given
        cleanAndInsertFakeData()

        // When
        val resultOf = recentSearchRepository.getLastFiveRecentSearches()

        // Then
        val limit = 5
        assert(resultOf is ResultOf.Success)
        val models = (resultOf as ResultOf.Success).value
        assertEquals(limit, models.size)

        var fakeEntityIndex = 7
        for (modemItemIndex in 0 until limit) {
            assertEquals(fakeRecentSearches[fakeEntityIndex].id, models[modemItemIndex].id)
            assertEquals(fakeRecentSearches[fakeEntityIndex].query, models[modemItemIndex].query)
            assertEquals(fakeRecentSearches[fakeEntityIndex].datetime, models[modemItemIndex].datetime)
            fakeEntityIndex -= 1
        }
    }

    @Test
    fun `test recent search repository delete all`() = runBlocking {
        // Given
        cleanAndInsertFakeData()

        // When
        val resultOf = recentSearchRepository.deleteAllRecentSearches()

        // Then
        assert(resultOf is ResultOf.Success)
        val success = (resultOf as ResultOf.Success).value
        assert(success)
    }

    private suspend fun cleanAndInsertFakeData() {
        recentSearchDao.deleteAll()
        fakeRecentSearches.forEach {
            recentSearchDao.insert(it)
        }
    }
}
