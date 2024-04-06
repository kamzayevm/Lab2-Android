package com.example.lab2.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
                .addHeader("X-Api-Key", "4LVmCh8PK31ejg2LRlzfjHt8HTuTHZYS68SQZXFf")
                .build()
            chain.proceed(newRequest)
        }
        .build()

    private const val BASE_URL = "https://api.api-ninjas.com/"

    val instance: ApiService by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}