package com.jmarkstar.gumtree_challenge.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import com.jmarkstar.gumtree_challenge.BuildConfig
import com.jmarkstar.gumtree_challenge.common.coroutines.CoroutineDispatcherProvider
import com.jmarkstar.gumtree_challenge.common.coroutines.DispatcherProvider
import com.jmarkstar.gumtree_challenge.common.util.NetworkCallbackImp
import com.jmarkstar.gumtree_challenge.common.util.NetworkState
import com.jmarkstar.gumtree_challenge.common.util.NetworkStateImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.HttpUrl

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @ApiKeyString
    @Singleton
    @Provides
    fun provideApiKey() = BuildConfig.API_KEY

    @Singleton
    @Provides
    fun provideBaseApiUrl() = HttpUrl.Builder()
        .scheme(BuildConfig.API_SCHEMA)
        .host(BuildConfig.API_HOST)
        .build()

    @Singleton
    @Provides
    fun provideNetworkState(@ApplicationContext context: Context): NetworkState {
        val holder = NetworkStateImp()
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().build(),
            NetworkCallbackImp(
                holder
            )
        )
        return holder
    }

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = CoroutineDispatcherProvider()
}
