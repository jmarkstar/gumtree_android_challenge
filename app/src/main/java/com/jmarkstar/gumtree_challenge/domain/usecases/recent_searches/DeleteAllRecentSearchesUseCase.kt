package com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches

import com.jmarkstar.gumtree_challenge.domain.ResultOf

interface DeleteAllRecentSearchesUseCase {

    suspend operator fun invoke(): ResultOf<Boolean>
}
