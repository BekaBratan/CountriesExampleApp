package com.example.countriesexampleapp
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("area")
    val area: Double,
    @SerializedName("borders")
    val borders: List<String>,
    @SerializedName("capital")
    val capital: String,
    @SerializedName("coordinates")
    val coordinates: Coordinates,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("languages")
    val languages: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("region")
    val region: String,
    @SerializedName("subregion")
    val subregion: String,
    @SerializedName("timezones")
    val timezones: List<String>
)