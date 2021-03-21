package com.jmarkstar.gumtree_challenge.domain.models

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

class RecentSearchModel(
    val id: Int,
    val query: String,
    val datetime: Long
) {
    private var recentSearchDateformatter = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.getDefault())

    fun datetimeFormatted() = recentSearchDateformatter.format(Date(datetime))
}
