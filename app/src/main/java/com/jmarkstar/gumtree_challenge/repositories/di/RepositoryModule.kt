package com.jmarkstar.gumtree_challenge.repositories.di

import com.jmarkstar.gumtree_challenge.domain.repositories.RecentSearchRepository
import com.jmarkstar.gumtree_challenge.domain.repositories.WeatherRepository
import com.jmarkstar.gumtree_challenge.repositories.RecentSearchRepositoryImpl
import com.jmarkstar.gumtree_challenge.repositories.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun provideRecentSearchRepository(
        recentSearchRepositoryImpl: RecentSearchRepositoryImpl
    ): RecentSearchRepository

    @ActivityRetainedScoped
    @Binds
    abstract fun provideWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}
