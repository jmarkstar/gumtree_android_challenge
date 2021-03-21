package com.jmarkstar.gumtree_challenge.domain.usecases.weather

import com.jmarkstar.gumtree_challenge.domain.repositories.WeatherRepository
import javax.inject.Inject

class GetWeatherByQueryUseCaseImpl @Inject constructor(
    private val weatherRepository: WeatherRepository
) : GetWeatherByQueryUseCase {

    override suspend fun invoke(query: String) = weatherRepository.getWeatherBy(query)
}
