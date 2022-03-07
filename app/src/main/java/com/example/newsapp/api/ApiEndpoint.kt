package com.example.newsapp.api

import com.example.newsapp.model.Articles
import com.example.newsapp.model.TopHeadlineModel
import com.squareup.okhttp.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("top-headlines")
    suspend fun getTopHeadline(
        @Query("country") country: String,
        @Query("apiKey") api_key: String
    ): Response<TopHeadlineModel>
}