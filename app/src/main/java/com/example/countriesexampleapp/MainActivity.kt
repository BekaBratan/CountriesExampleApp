package com.example.countriesexampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountryApp()
        }
    }
}

@Composable
fun CountryApp(){
    val retrofit = Retrofit.Builder()
        .baseUrl("https://countries-api-abhishek.vercel.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val services = retrofit.create(CountryExampleService::class.java)

    var countryDataResponse by remember { mutableStateOf<Data?>(null) }
    var errorDataResponse by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO){
            try {
                val response = services.getCountry()
                countryDataResponse = response.data
            } catch (e: Exception){
                errorDataResponse = e.localizedMessage
            }
        }
    }

    if (countryDataResponse!=null){
        CountryScreen(countryData = countryDataResponse!!)
    }else if (errorDataResponse!=null){
        Text("Error: $errorDataResponse")
    }else{
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }
}



