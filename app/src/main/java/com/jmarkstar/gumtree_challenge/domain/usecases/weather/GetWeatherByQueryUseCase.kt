package com.jmarkstar.gumtree_challenge.domain.usecases.weather

import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.models.WeatherModel

interface GetWeatherByQueryUseCase {

    suspend operator fun invoke(query: String): ResultOf<WeatherModel>
}
