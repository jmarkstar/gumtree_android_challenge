package com.jmarkstar.gumtree_challenge.presentation.di

import com.jmarkstar.gumtree_challenge.presentation.recent_searches.RecentSearchAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @FragmentScoped
    @Provides
    fun provideRecentSearchAdapter() = RecentSearchAdapter()
}
