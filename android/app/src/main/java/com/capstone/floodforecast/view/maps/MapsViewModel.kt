package com.capstone.floodforecast.view.maps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.floodforecast.data.Location
import com.capstone.floodforecast.data.LocationRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MapsViewModel(private val locationRepository: LocationRepository) : ViewModel() {
    private val _locations = MutableLiveData<List<Location>>()
    val locations: LiveData<List<Location>> = _locations

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getLocations() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val locationsList = locationRepository.getLocations()
                _locations.value = locationsList?.filterNotNull()
                Log.d("MapsViewModel", "Locations updated: $locationsList")
            } catch (e: Exception) {
                Log.e("MapsViewModel", "Error fetching stories", e)
            } finally {
                _loading.value = false
            }
        }
    }
}