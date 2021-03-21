package com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches

import com.jmarkstar.gumtree_challenge.domain.repositories.RecentSearchRepository
import javax.inject.Inject

class GetAllRecentSearchesUseCaseImpl @Inject constructor(
    private val recentSearchRepository: RecentSearchRepository
) : GetAllRecentSearchesUseCase {

    override suspend fun invoke() = recentSearchRepository.getAllRecentSearches()
}
