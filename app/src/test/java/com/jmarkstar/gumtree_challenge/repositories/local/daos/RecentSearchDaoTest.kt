package com.jmarkstar.gumtree_challenge.repositories.local.daos

import com.jmarkstar.gumtree_challenge.common.BaseTest
import com.jmarkstar.gumtree_challenge.fakeRecentSearches
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class RecentSearchDaoTest : BaseTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var recentSearchDao: RecentSearchDao

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `test recent searches dao cases`() = runBlocking {

        assertEquals(0, recentSearchDao.count())

        fakeRecentSearches.forEach {
            recentSearchDao.insert(it)
        }

        assertEquals(fakeRecentSearches.size, recentSearchDao.count())

        assert(fakeRecentSearches.containsAll(recentSearchDao.getAll()))

        recentSearchDao.deleteAll()

        assertEquals(0, recentSearchDao.count())
    }
}
