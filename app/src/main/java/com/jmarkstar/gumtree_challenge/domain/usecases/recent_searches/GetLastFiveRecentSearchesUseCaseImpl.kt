package com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches

import com.jmarkstar.gumtree_challenge.domain.repositories.RecentSearchRepository
import javax.inject.Inject

class GetLastFiveRecentSearchesUseCaseImpl @Inject constructor(
    private val recentSearchRepository: RecentSearchRepository
) : GetLastFiveRecentSearchesUseCase {

    override suspend fun invoke() = recentSearchRepository.getLastFiveRecentSearches()
}
