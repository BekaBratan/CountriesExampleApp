package com.example.countriesexampleapp

import retrofit2.http.GET

interface CountryExampleService {
    companion object{
        const val COUNTRY_URL = "countries/kazakhstan"
    }

    @GET(COUNTRY_URL)
    suspend fun getCountry(): CountryServiceResponse
}