package com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches

import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.models.RecentSearchModel

interface GetAllRecentSearchesUseCase {

    suspend operator fun invoke(): ResultOf<List<RecentSearchModel>>
}
