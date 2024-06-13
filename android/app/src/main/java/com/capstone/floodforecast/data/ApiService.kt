package com.capstone.floodforecast.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("locations")
    suspend fun getLocations(
        @Query("location") location : Int = 1,
    ): LocationResponse
}
