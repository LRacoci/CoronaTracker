package com.github.coronatracker.data

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Url

interface CoronaApi {
    @GET("")
    fun getAsync(@Url url: String): Deferred<CoronaResponse>
}
