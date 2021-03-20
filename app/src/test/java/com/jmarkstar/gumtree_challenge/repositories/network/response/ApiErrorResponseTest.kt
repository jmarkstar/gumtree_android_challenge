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
class ApiErrorResponseTest : BaseTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var gson: Gson

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `test parsing get weather api error not found`() {
        val jsonString = UnitTestUtils.readFileFromResources("get_weather_response_not_found.json")

        val response = gson.fromJson(jsonString, ApiErrorResponse::class.java)
        assertNotNull(response)
        assertEquals("city not found", response.message)
    }

    @Test
    fun `test parsing get weather api error not allowed`() {
        val jsonString = UnitTestUtils.readFileFromResources("get_weather_response_not_allowed.json")

        val response = gson.fromJson(jsonString, ApiErrorResponse::class.java)
        assertNotNull(response)
        assertEquals(
            "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.",
            response.message
        )
    }
}
