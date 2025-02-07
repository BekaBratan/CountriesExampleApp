package com.example.countriesexampleapp

import retrofit2.http.GET
import retrofit2.http.Path

interface CountryExampleService {
    companion object{
        const val COUNTRY_URL = "countries/"
    }

    @GET("$COUNTRY_URL{name}")
    suspend fun getCountry(
        @Path("name") countryName: String
    ): CountryServiceResponse

    @GET(COUNTRY_URL)
    suspend fun getCountries(): CountriesServiceResponse
}