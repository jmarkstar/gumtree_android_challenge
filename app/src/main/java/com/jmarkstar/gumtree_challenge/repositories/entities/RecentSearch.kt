package com.jmarkstar.gumtree_challenge.repositories.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.jmarkstar.gumtree_challenge.domain.models.RecentSearchModel

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

fun RecentSearch.toModel(): RecentSearchModel {
    val checkedId = checkNotNull(id) {
        throw IllegalArgumentException("RecentSearch:id should not be null")
    }

    return RecentSearchModel(checkedId, query, datetime)
}

fun List<RecentSearch>.toModels() = map { it.toModel() }
