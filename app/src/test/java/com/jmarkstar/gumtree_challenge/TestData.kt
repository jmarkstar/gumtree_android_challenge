package com.jmarkstar.gumtree_challenge

import com.jmarkstar.gumtree_challenge.domain.models.WeatherModel
import com.jmarkstar.gumtree_challenge.repositories.entities.RecentSearch
import com.jmarkstar.gumtree_challenge.repositories.entities.toModels
import java.util.Date

private val dateTime = Date().time

val fakeRecentSearches = arrayListOf(
    RecentSearch(1, "2067, AU", dateTime),
    RecentSearch(2, "Brisbane, AU", dateTime),
    RecentSearch(3, "Lima, PE", dateTime),
    RecentSearch(4, "Arequipa, PE", dateTime),
    RecentSearch(5, "Sydney, AU", dateTime),
    RecentSearch(6, "7A Johnson street, Chatswood, AU", dateTime),
    RecentSearch(7, "46 AV Arequipa, Lima, PE", dateTime),
    RecentSearch(8, "Avenida Siempre Viva, Lima, PE", dateTime)
)

private val lastIndex = fakeRecentSearches.size - 1
val fakeLastFiveSearchModels = fakeRecentSearches.toModels().subList(lastIndex - 5, lastIndex)

val fakeWeather = WeatherModel(
    "Chatswood",
    "AU",
    "Rain",
    "moderate rain",
    "http://openweathermap.org/img/wn/10n@2x.png",
    20,
    23,
    70
)
