package com.example.tokotekkotek.model

import com.google.gson.annotations.SerializedName

data class DataUserRegist(
    @SerializedName("email")
    val email: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String
)