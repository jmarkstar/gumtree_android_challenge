package com.jmarkstar.gumtree_challenge.repositories.network

import com.jmarkstar.gumtree_challenge.common.UnitTestUtils
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

object MockServerDispatchers {

    val providerWeatherSuccessResponse = object : Dispatcher() {

        override fun dispatch(
            request: RecordedRequest
        ): MockResponse {
            return when (request.path) {
                "/data/2.5/weather" ->
                    UnitTestUtils.mockJsonResponse(200, "get_weather_response_success.json")

                else ->
                    UnitTestUtils.mockJsonResponse(401, "get_weather_response_not_allowed.json")
            }
        }
    }

    val providerWeatherNotFoundResponse = object : Dispatcher() {

        override fun dispatch(
            request: RecordedRequest
        ): MockResponse {
            return when (request.path) {
                "/data/2.5/weather" ->
                    UnitTestUtils.mockJsonResponse(404, "get_weather_response_not_found.json")

                else ->
                    UnitTestUtils.mockJsonResponse(401, "get_weather_response_not_allowed.json")
            }
        }
    }
}
