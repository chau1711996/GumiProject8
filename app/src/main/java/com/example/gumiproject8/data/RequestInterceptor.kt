package com.example.gumiproject8.data

import android.util.Log
import com.example.gumiproject8.utils.Constants
import com.example.gumiproject8.utils.MyHashCode
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val timeStamp = System.currentTimeMillis()
        Log.d("HashCode", timeStamp.toString())
        val originalRequest = chain.request()
        val originalHttpUrl = chain.request().url()
        val url = originalHttpUrl.newBuilder().apply {
            addQueryParameter("apikey", Constants.PUBLIC_API_KEY)
            addQueryParameter(
                "hash",
                MyHashCode.generate(timeStamp, Constants.PRIVATE_API_KEY, Constants.PUBLIC_API_KEY)
            )
            addQueryParameter("ts", timeStamp.toString())
        }.build()
        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}