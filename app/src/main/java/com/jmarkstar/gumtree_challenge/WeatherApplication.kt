package com.jmarkstar.gumtree_challenge

import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.jmarkstar.gumtree_challenge.common.BaseApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WeatherApplication : BaseApplication() {

    @Inject
    lateinit var imagePipelineConfig: ImagePipelineConfig

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this, imagePipelineConfig)
    }
}
