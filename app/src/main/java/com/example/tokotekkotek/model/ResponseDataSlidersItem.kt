package com.example.tokotekkotek.model


import com.google.gson.annotations.SerializedName

data class ResponseDataSlidersItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String
)