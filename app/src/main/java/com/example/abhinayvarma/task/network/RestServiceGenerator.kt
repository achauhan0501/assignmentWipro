package com.example.abhinayvarma.task.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestServiceGenerator {

    companion object {

        fun <T> createRestApiService(serviceClass: Class<T>, baseUrl: String): T {

            val httpClient = OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
            httpClient.build()


            val builder = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return builder.create(serviceClass)
        }


    }
}