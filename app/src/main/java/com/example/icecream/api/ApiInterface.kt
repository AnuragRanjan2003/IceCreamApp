package com.example.icecream.api

import com.example.icecream.constants.GET_END_POINT
import com.example.icecream.models.api.Chat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET(GET_END_POINT)
    suspend fun getChat(
        @Query("text")
        text : String) : Response<Chat>
}