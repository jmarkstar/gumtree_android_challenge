package com.jmarkstar.gumtree_challenge

import com.facebook.drawee.backends.pipeline.Fresco
import com.jmarkstar.gumtree_challenge.common.BaseApplication

// Issue with this ticket -> https://github.com/google/dagger/issues/2033
// In needed to create my TestApplication without any @Inject
open class WeatherTestApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
    }
}

