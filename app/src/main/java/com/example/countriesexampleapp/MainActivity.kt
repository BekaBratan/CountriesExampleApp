package com.example.countriesexampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.unit.dp
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
    var countriesDataResponse by remember { mutableStateOf<List<Data>?>(null) }
    var errorDataResponse by remember { mutableStateOf<String?>(null) }
    var countryName by remember { mutableStateOf<String>("") }
    var showCountry by remember { mutableStateOf(false) }
    var showCountries by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    if (showCountry){
        LaunchedEffect(Unit) {
            coroutineScope.launch(Dispatchers.IO){
                try {
                    val response = services.getCountry(countryName)
                    countryDataResponse = response.data
                } catch (e: Exception){
                    errorDataResponse = e.localizedMessage
                }
            }
        }
    } else if (showCountries){
        LaunchedEffect(Unit) {
            coroutineScope.launch(Dispatchers.IO){
                try {
                    val response = services.getCountries()
                    countriesDataResponse = response.data
                } catch (e: Exception){
                    errorDataResponse = e.localizedMessage
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp, 48.dp, 16.dp, 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (countryDataResponse!=null){
            CountryScreen(countryData = countryDataResponse!!)
            showCountry = false
            showCountries = false
        }else if (countriesDataResponse!=null){
            Button(
                onClick = {
                    countryDataResponse = null
                    countriesDataResponse = null
                    errorDataResponse = null
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Back")
            }
            CountriesScreen(countriesData = countriesDataResponse!!)
            showCountry = false
            showCountries = false
        }else if (errorDataResponse!=null){
            Text("Error: $errorDataResponse")
            showCountry = false
            showCountries = false
        }else if (showCountry || showCountries){
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }

            Spacer(modifier = Modifier.size(16.dp))
        }

        if (countriesDataResponse==null){
            OutlinedTextField(
                value = countryName,
                onValueChange = { countryName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text("Country name") }
            )

            Button(
                onClick = {
                    showCountry = true
                    countryDataResponse = null
                    countriesDataResponse = null
                    errorDataResponse = null
                }
            ) {
                Text("Search")
            }

            Button(
                onClick = {
                    showCountries = true
                    countryDataResponse = null
                    countriesDataResponse = null
                    errorDataResponse = null
                }
            ) {
                Text("Show All Countries")
            }
        }
    }
}



