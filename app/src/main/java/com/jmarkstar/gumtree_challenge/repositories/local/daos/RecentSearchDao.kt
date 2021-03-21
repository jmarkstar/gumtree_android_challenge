package com.jmarkstar.gumtree_challenge.repositories.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmarkstar.gumtree_challenge.repositories.entities.RecentSearch

@Dao
interface RecentSearchDao {

    @Query("SELECT COUNT(id) FROM recent_searches")
    suspend fun count(): Int

    @Query("SELECT * FROM recent_searches")
    suspend fun getAll(): List<RecentSearch>

    @Query("SELECT * FROM recent_searches ORDER BY id desc LIMIT 5 ")
    suspend fun getLastFive(): List<RecentSearch>

    @Query("DELETE FROM recent_searches")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(query: RecentSearch)
}
