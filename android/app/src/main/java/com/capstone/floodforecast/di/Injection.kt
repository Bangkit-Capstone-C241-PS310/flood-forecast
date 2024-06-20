package com.capstone.floodforecast.di

import android.content.Context
import com.capstone.floodforecast.data.ApiConfig
import com.capstone.floodforecast.data.LocationRepository

object Injection {
    fun provideRepository(context: Context): LocationRepository {
        val apiService = ApiConfig.getApiService()
        return LocationRepository.getInstance(apiService)
    }
}