package com.jmarkstar.gumtree_challenge.repositories.di

import android.content.Context
import androidx.room.Room
import com.jmarkstar.gumtree_challenge.repositories.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ) =
        Room.databaseBuilder(
            appContext,
            WeatherDatabase::class.java,
            "movies.db"
        )
            .build()

    @Provides
    fun provideRecentSearchDao(database: WeatherDatabase) = database.recentSearchDao
}
