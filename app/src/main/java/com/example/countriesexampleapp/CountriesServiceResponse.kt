package com.example.countriesexampleapp

import com.google.gson.annotations.SerializedName

data class CountriesServiceResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("statusCode")
    val statusCode: Int
)
