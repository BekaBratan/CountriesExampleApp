package com.example.countriesexampleapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest


@Composable
fun CountryScreen(countryData: Data){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(countryData.flag)
                    .decoderFactory(SvgDecoder.Factory())
                    .build()
            ),
            contentDescription = "Country Flag",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(16.dp))
        Text(countryData.name, fontSize = 32.sp)
        Spacer(modifier = Modifier.size(16.dp))
        Text("Capital: ${countryData.capital}", fontSize = 24.sp)
        Spacer(modifier = Modifier.size(16.dp))
        Text("${countryData.coordinates}", fontSize = 16.sp)
        Spacer(modifier = Modifier.size(16.dp))
        Text("Population: ${countryData.population}", fontSize = 16.sp)
    }
}