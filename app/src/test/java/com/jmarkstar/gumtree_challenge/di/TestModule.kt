package com.jmarkstar.gumtree_challenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideTestCoroutineDispatcher() = TestCoroutineDispatcher()
}
