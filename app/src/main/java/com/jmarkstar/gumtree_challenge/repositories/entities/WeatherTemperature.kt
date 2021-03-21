package com.jmarkstar.gumtree_challenge.repositories.entities

import com.google.gson.annotations.SerializedName

data class WeatherTemperature(
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    val humidity: Int
)
