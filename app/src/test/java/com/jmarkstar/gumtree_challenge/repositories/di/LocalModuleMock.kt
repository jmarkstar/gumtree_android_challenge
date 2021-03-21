package com.jmarkstar.gumtree_challenge.repositories.di

import android.content.Context
import androidx.room.Room
import com.jmarkstar.gumtree_challenge.repositories.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [LocalModule::class]
)
object LocalModuleMock {

    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): WeatherDatabase =
        Room.inMemoryDatabaseBuilder(
            appContext,
            WeatherDatabase::class.java
        )
            .build()

    @Provides
    fun provideRecentSearchDao(database: WeatherDatabase) = database.recentSearchDao
}
