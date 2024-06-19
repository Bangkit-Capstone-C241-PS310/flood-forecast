package com.capstone.floodforecast.view.maps

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.capstone.floodforecast.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.capstone.floodforecast.databinding.FragmentMapsBinding
import com.google.android.gms.maps.model.LatLngBounds
import kotlinx.coroutines.launch
import com.capstone.floodforecast.di.Injection
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.model.BitmapDescriptorFactory

class MapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private val mapsViewModel: MapsViewModel by viewModels {
        MapsViewModelFactory(Injection.provideRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
            isCompassEnabled = true
            isIndoorLevelPickerEnabled = true
            isMapToolbarEnabled = true
        }

        setupObservers()
        getMyLocation()
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }

    private fun setupObservers() {
        mapsViewModel.getLocations()

        mapsViewModel.loading.observe(this) { loading ->
            setLoadingState(loading)
        }

        mapsViewModel.locations.observe(this) { locations ->
            lifecycleScope.launch {
                if (locations != null) {
                    val boundsBuilder = LatLngBounds.Builder()
                    locations.forEach { data ->
                        val latLng = data.lat?.let { data.lon?.let { it1 -> LatLng(it, it1) } }
                        latLng?.let {
                            val color = data.color?.let { hexColor ->
                                val parsedColor = Color.parseColor(hexColor)
                                val hsv = FloatArray(3)
                                Color.colorToHSV(parsedColor, hsv)
                                hsv[0]
                            } ?: BitmapDescriptorFactory.HUE_RED

                            mMap.addMarker(MarkerOptions()
                                .position(it)
                                .icon(BitmapDescriptorFactory.defaultMarker(color))
                            )
                            boundsBuilder.include(it)
                        }
                    }
                }
            }
        }

        val jakarta = LatLng(-6.25, 106.83)
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                jakarta, 11f
            )
        )
    }

    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext().applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setLoadingState(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}