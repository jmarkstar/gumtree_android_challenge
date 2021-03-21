package com.jmarkstar.gumtree_challenge.domain.repositories

import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.models.WeatherModel

interface WeatherRepository {

    suspend fun getWeatherBy(query: String, country: String): ResultOf<WeatherModel>
}
