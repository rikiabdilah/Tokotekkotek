package com.example.tokotekkotek.model


import com.google.gson.annotations.SerializedName

data class ResponseNewsUpdateItem(
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id_news")
    val idNews: String,
    @SerializedName("news_image")
    val newsImage: String,
    @SerializedName("title")
    val title: String
)