package com.capstone.floodforecast.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("predict")
    suspend fun getLocations(
    ): LocationResponse
}
