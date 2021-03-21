package com.jmarkstar.gumtree_challenge.repositories.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jmarkstar.gumtree_challenge.repositories.entities.RecentSearch
import com.jmarkstar.gumtree_challenge.repositories.local.daos.RecentSearchDao

@Database(
    entities = [
        RecentSearch::class
    ],
    version = 1,
    exportSchema = true
)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val recentSearchDao: RecentSearchDao
}
