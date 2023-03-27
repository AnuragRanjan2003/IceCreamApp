package com.example.icecream.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChatApi {
    fun getInstance(url: String): ApiInterface {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(ApiInterface::class.java)
    }
}