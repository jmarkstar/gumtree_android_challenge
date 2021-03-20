package com.jmarkstar.gumtree_challenge

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        if (BuildConfig.BUILD_TYPE == "debug") {
            assertEquals("com.jmarkstar.gumtree_challenge.debug", appContext.packageName)
        } else {
            assertEquals("com.jmarkstar.gumtree_challenge", appContext.packageName)
        }
    }
}