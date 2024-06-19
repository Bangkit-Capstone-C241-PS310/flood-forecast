package com.capstone.floodforecast.data

import retrofit2.http.GET

interface ApiService {
    @GET("predict")
    suspend fun getLocations(
    ): LocationResponse
}
