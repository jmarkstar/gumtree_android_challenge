package com.jmarkstar.gumtree_challenge.repositories.network.response

import com.google.gson.annotations.SerializedName
import com.jmarkstar.gumtree_challenge.repositories.entities.System
import com.jmarkstar.gumtree_challenge.repositories.entities.Weather
import com.jmarkstar.gumtree_challenge.repositories.entities.WeatherTemperature

data class GetWeatherResponse(
    val name: String,
    val weather: List<Weather>,
    val sys: System,
    @SerializedName("main") val temperature: WeatherTemperature,
    @SerializedName("dt") val dateTime: Long,
    val timezone: Long,
)
