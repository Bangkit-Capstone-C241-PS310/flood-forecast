package com.capstone.floodforecast.view.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.floodforecast.data.LocationRepository

class MapsViewModelFactory (
    private val locationRepository: LocationRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MapsViewModel(locationRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
