package com.jmarkstar.gumtree_challenge

import com.jmarkstar.gumtree_challenge.repositories.entities.RecentSearch
import java.util.Date

private val dateTime = Date().time

val fakeRecentSearches = arrayListOf(
    RecentSearch(1, "2067, AU", dateTime),
    RecentSearch(2, "Brisbane, AU", dateTime),
    RecentSearch(3, "Lima, PE", dateTime),
    RecentSearch(4, "Arequipa, PE", dateTime)
)
