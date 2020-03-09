package com.github.coronatracker.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.coronatracker.data.CoronaApi
import com.github.coronatracker.data.CoronaResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    val api: CoronaApi = Retrofit.Builder()
        .client(OkHttpClient.Builder().build())
        .baseUrl("https://api-coronavirus.herokuapp.com")
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CoronaApi::class.java)

    suspend fun getCoronaData(): CoronaResponse = withContext(viewModelScope.coroutineContext) {
        api.getAsync("https://api-coronavirus.herokuapp.com").await()
    }
}
