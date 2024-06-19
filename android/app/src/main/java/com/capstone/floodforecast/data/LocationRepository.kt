package com.capstone.floodforecast.data

class LocationRepository private constructor(
    private val apiService: ApiService
) {

    suspend fun getLocations(): List<Location?>? {
        return apiService.getLocations().data
    }

    companion object {
        @Volatile
        private var INSTANCE: LocationRepository? = null

        fun getInstance(apiService: ApiService): LocationRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = LocationRepository(apiService)
                INSTANCE = instance
                instance
            }
        }
    }
}