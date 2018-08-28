package com.example.abhinayvarma.task.network

import com.example.abhinayvarma.task.model.ResponseData
import retrofit2.Call
import retrofit2.http.GET

interface ApiContract {
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getData(): Call<ResponseData>
}