package com.jmarkstar.gumtree_challenge.repositories.network.interceptors

import com.jmarkstar.gumtree_challenge.repositories.network.API_KEY_QUERY_PARAM_NAME
import okhttp3.Interceptor
import okhttp3.Response

class AddApiKeyQueryParamInterceptor(private val apiKeyQueryParam: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(API_KEY_QUERY_PARAM_NAME, apiKeyQueryParam).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
