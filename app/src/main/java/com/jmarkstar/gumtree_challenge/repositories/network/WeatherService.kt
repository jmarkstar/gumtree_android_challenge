package com.jmarkstar.gumtree_challenge.repositories.network

import com.jmarkstar.gumtree_challenge.repositories.network.response.GetWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY_QUERY_PARAM_NAME = "appid"

interface WeatherService {

    /* Gets weather information
     * `q`: {city name} or {city name},{state code} or {city name},{state code},{country code}
     * `zip`: {zip code},{country code}
     * `units`: metric=Celsius, imperial=Fahrenheit and by default is standard=kelvin
     * `lang`: Translation is applied for the city name and description fields.
     *
     * Use ISO 3166 for {country code}
     * */
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("zip") zipCode: String,
        @Query("lang") lang: String,
        @Query("units") units: String = "metric"
    ): Response<GetWeatherResponse>
}
