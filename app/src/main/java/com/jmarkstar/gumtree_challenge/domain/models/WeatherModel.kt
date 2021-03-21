package com.jmarkstar.gumtree_challenge.domain.models

class WeatherModel(
    val place: String,
    val country: String,
    val name: String,
    val description: String,
    val iconUrl: String,
    val temperature: Int,
    val feelsLike: Int,
    val humidity: Int
)
