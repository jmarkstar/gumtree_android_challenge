package com.jmarkstar.gumtree_challenge.common

import android.app.Application
import com.jmarkstar.gumtree_challenge.BuildConfig
import com.jmarkstar.gumtree_challenge.R
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupAppLogging()
        setupFonts()
    }

    private fun setupAppLogging() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    private fun setupFonts() {
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/SF-UI-Text-Regular.otf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                ).build()
        )
    }
}
