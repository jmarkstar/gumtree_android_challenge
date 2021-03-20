package com.jmarkstar.gumtree_challenge.repositories.network.response

import com.google.gson.Gson
import com.jmarkstar.gumtree_challenge.common.BaseTest
import com.jmarkstar.gumtree_challenge.common.UnitTestUtils
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class GetWeatherResponseTest : BaseTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var gson: Gson

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `test parsing get weather successfully`() {
        val jsonString = UnitTestUtils.readFileFromResources("get_weather_response_success.json")

        val response = gson.fromJson(jsonString, GetWeatherResponse::class.java)

        assertNotNull(response)
        assertEquals("Chatswood West", response.name)
        assertEquals(1616228430, response.dateTime)
        assertEquals(39600, response.timezone)

        assertEquals(501, response.weather[0].conditionId)
        assertEquals("Rain", response.weather[0].generalName)
        assertEquals("moderate rain", response.weather[0].description)
        assertEquals("10n", response.weather[0].iconName)

        assertEquals(19.87, response.temperature.temp, 0.0)
        assertEquals(23.04, response.temperature.feelsLike, 0.0)
        assertEquals(98, response.temperature.humidity)
    }
}
