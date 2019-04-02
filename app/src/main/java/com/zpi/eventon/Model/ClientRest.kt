package com.zpi.eventon.Model

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

object ClientRest {


    private val httpClient = OkHttpClient.Builder()

    fun getRestClient(token: String): OkHttpClient {
        httpClient.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()

                val request = original.newBuilder()
                    .header("token", token)
                    .method(original.method(), original.body())
                    .build()

                return chain.proceed(request)
            }
        })
        return httpClient.build()

    }

}
