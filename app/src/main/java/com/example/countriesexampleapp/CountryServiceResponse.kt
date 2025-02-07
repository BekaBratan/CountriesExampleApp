package com.example.countriesexampleapp
import com.google.gson.annotations.SerializedName

data class CountryServiceResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("statusCode")
    val statusCode: Int
)