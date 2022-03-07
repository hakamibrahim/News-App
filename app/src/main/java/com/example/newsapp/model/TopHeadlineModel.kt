package com.example.newsapp.model

import com.google.gson.annotations.SerializedName


data class TopHeadlineModel (

  @SerializedName("status")
  var status: String,

  @SerializedName("totalResults")
  var totalResults : Int,

  @SerializedName("articles")
  var articles : ArrayList<Articles> = arrayListOf()

)