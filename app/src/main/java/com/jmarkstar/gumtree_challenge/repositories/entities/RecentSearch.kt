package com.jmarkstar.gumtree_challenge.repositories.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = RecentSearch.TABLE_NAME,
    indices = [Index("id")]
)

data class RecentSearch(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val query: String,
    val datetime: Long
) {
    companion object {
        const val TABLE_NAME = "recent_searches"
    }

    constructor(query: String, datetime: Long) : this(null, query, datetime)
}
