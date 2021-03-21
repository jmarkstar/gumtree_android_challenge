package com.jmarkstar.gumtree_challenge.presentation.di

import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.DeleteAllRecentSearchesUseCase
import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.DeleteAllRecentSearchesUseCaseImpl
import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.GetAllRecentSearchesUseCase
import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.GetAllRecentSearchesUseCaseImpl
import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.GetLastFiveRecentSearchesUseCase
import com.jmarkstar.gumtree_challenge.domain.usecases.recent_searches.GetLastFiveRecentSearchesUseCaseImpl
import com.jmarkstar.gumtree_challenge.domain.usecases.weather.GetWeatherByQueryUseCase
import com.jmarkstar.gumtree_challenge.domain.usecases.weather.GetWeatherByQueryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCasesModule {

    @ViewModelScoped
    @Binds
    abstract fun provideGetWeatherByQueryUseCase(
        getWeatherByQueryUseCaseImpl: GetWeatherByQueryUseCaseImpl
    ): GetWeatherByQueryUseCase

    @ViewModelScoped
    @Binds
    abstract fun provideDeleteAllRecentSearchesUseCase(
        deleteAllRecentSearchesUseCaseImpl: DeleteAllRecentSearchesUseCaseImpl
    ): DeleteAllRecentSearchesUseCase

    @ViewModelScoped
    @Binds
    abstract fun provideGetAllRecentSearchesUseCase(
        getAllRecentSearchesUseCaseImpl: GetAllRecentSearchesUseCaseImpl
    ): GetAllRecentSearchesUseCase

    @ViewModelScoped
    @Binds
    abstract fun provideGetLastFiveRecentSearchesUseCase(
        getLastFiveRecentSearchesUseCaseImpl: GetLastFiveRecentSearchesUseCaseImpl
    ): GetLastFiveRecentSearchesUseCase
}
