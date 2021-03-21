package com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches

import com.jmarkstar.gumtree_challenge.domain.repositories.RecentSearchRepository
import javax.inject.Inject

class DeleteAllRecentSearchesUseCaseImpl @Inject constructor(
    private val recentSearchRepository: RecentSearchRepository
) : DeleteAllRecentSearchesUseCase {

    override suspend fun invoke() = recentSearchRepository.deleteAllRecentSearches()
}
