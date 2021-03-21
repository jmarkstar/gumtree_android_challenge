package com.jmarkstar.gumtree_challenge.repositories

import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.repositories.RecentSearchRepository
import com.jmarkstar.gumtree_challenge.repositories.entities.toModels
import com.jmarkstar.gumtree_challenge.repositories.exceptions.DatabaseException
import com.jmarkstar.gumtree_challenge.repositories.local.daos.RecentSearchDao
import javax.inject.Inject

class RecentSearchRepositoryImpl @Inject constructor(
    private val recentSearchDao: RecentSearchDao
) : RecentSearchRepository {

    override suspend fun getLastFiveRecentSearches() = try {
        ResultOf.Success(recentSearchDao.getLastFive().toModels())
    } catch (ex: Exception) {
        ResultOf.Failure(DatabaseException())
    }

    override suspend fun getAllRecentSearches() = try {
        ResultOf.Success(recentSearchDao.getAll().toModels())
    } catch (ex: Exception) {
        ResultOf.Failure(DatabaseException())
    }

    override suspend fun deleteAllRecentSearches() = try {
        recentSearchDao.deleteAll()
        ResultOf.Success(true)
    } catch (ex: Exception) {
        ResultOf.Failure(DatabaseException())
    }
}
