package com.jmarkstar.gumtree_challenge.repositories

import android.database.sqlite.SQLiteException
import com.google.gson.Gson
import com.jmarkstar.gumtree_challenge.common.util.NetworkState
import com.jmarkstar.gumtree_challenge.domain.ResultOf
import com.jmarkstar.gumtree_challenge.domain.models.WeatherModel
import com.jmarkstar.gumtree_challenge.domain.repositories.WeatherRepository
import com.jmarkstar.gumtree_challenge.repositories.entities.RecentSearch
import com.jmarkstar.gumtree_challenge.repositories.exceptions.DatabaseException
import com.jmarkstar.gumtree_challenge.repositories.exceptions.NetworkException
import com.jmarkstar.gumtree_challenge.repositories.exceptions.NotInternetException
import com.jmarkstar.gumtree_challenge.repositories.local.daos.RecentSearchDao
import com.jmarkstar.gumtree_challenge.repositories.network.API_IMAGE_FORMAT
import com.jmarkstar.gumtree_challenge.repositories.network.WeatherService
import com.jmarkstar.gumtree_challenge.repositories.network.response.ApiErrorResponse
import java.io.IOException
import java.util.Date
import javax.inject.Inject
import kotlin.math.roundToInt

class WeatherRepositoryImpl@Inject constructor(
    private val recentSearchDao: RecentSearchDao,
    private val weatherService: WeatherService,
    private val networkState: NetworkState,
    private val gson: Gson
) : WeatherRepository {

    override suspend fun getWeatherBy(query: String) = if (networkState.isConnected) {
        weatherService.getWeather(query).let { response ->
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let { weatherResponse ->
                    try {
                        val weather = WeatherModel(
                            weatherResponse.name,
                            weatherResponse.sys.countryCode,
                            weatherResponse.weather[0].generalName,
                            weatherResponse.weather[0].description,
                            String.format(API_IMAGE_FORMAT, weatherResponse.weather[0].iconName),
                            weatherResponse.temperature.temp.roundToInt(),
                            weatherResponse.temperature.feelsLike.roundToInt(),
                            weatherResponse.temperature.humidity
                        )
                        recentSearchDao.insert(RecentSearch(query, Date().time))
                        ResultOf.Success(weather)
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                        ResultOf.Failure(NetworkException(response.code(), "Error Parsing response json"))
                    } catch (ex: SQLiteException) {
                        ex.printStackTrace()
                        ResultOf.Failure(DatabaseException())
                    }
                } ?: kotlin.run {
                    ResultOf.Failure(NetworkException(response.code(), "Error processing http response: body is null"))
                }
            } else {
                response.errorBody()?.let {
                    val errorResponse = gson.fromJson(it.string(), ApiErrorResponse::class.java)
                    ResultOf.Failure(NetworkException(response.code(), errorResponse.message))
                } ?: kotlin.run {
                    ResultOf.Failure(NetworkException(response.code(), "Error processing http response: error body is null"))
                }
            }
        }
    } else {
        ResultOf.Failure(NotInternetException())
    }
}
