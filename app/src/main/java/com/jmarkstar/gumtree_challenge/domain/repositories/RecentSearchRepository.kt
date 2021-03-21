package com.jmarkstar.gumtree_challenge.domain.repositories

import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.models.RecentSearchModel

interface RecentSearchRepository {

    suspend fun getLastFiveRecentSearches(): ResultOf<List<RecentSearchModel>>
    suspend fun getAllRecentSearches(): ResultOf<List<RecentSearchModel>>
    suspend fun deleteAllRecentSearches(): ResultOf<Boolean>
}
