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
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.lifecycleScope
import com.capstone.floodforecast.databinding.FragmentMapsBinding
import com.google.android.gms.maps.model.LatLngBounds
import kotlinx.coroutines.launch
import com.capstone.floodforecast.di.Injection
import androidx.fragment.app.viewModels
import com.capstone.floodforecast.data.Location
import com.capstone.floodforecast.databinding.DetailMapsBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MapsFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var searchView: SearchView

    private lateinit var mMap: GoogleMap
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private var _detailBinding: DetailMapsBinding? = null
    private val detailBinding get() = _detailBinding!!
    private val mapsViewModel: MapsViewModel by viewModels {
        MapsViewModelFactory(Injection.provideRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        _detailBinding = DetailMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setupSearchView(view)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.apply {
            isZoomControlsEnabled = true
            isCompassEnabled = true
            isIndoorLevelPickerEnabled = true
            isMapToolbarEnabled = true
        }

        mMap.setOnMarkerClickListener(this)
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

    private fun setupSearchView(view: View) {
        searchView = view.findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    performSearch(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Implement live search if needed
                return true
            }
        })
    }

    private fun performSearch(query: String) {
        val foundLocation = mapsViewModel.locations.value?.find { it.locationName.equals(query, true) }
            ?: mapsViewModel.locations.value?.find { it.locationName?.contains(query, true) == true }

        foundLocation?.let {
            // Found the location, move camera to its position
            val latLng = LatLng(it.lat ?: 0.0, it.lon ?: 0.0)
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
            showDetails(it)
        } ?: run {
            // Handle case where location was not found
            Toast.makeText(requireContext(), "Location '$query' not found", Toast.LENGTH_SHORT).show()
        }

        // Collapse the search view after search
        searchView.clearFocus()
        searchView.isIconified = true
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
                                .title(data.locationName)
                            )?.tag = data
                            boundsBuilder.include(it)
                        }
                    }
                }
            }
        }

        val jakarta = LatLng(-6.23, 106.83)
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
        _detailBinding = null
    }

    private fun setLoadingState(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val location = marker.tag as? Location
        location?.let {
            showDetails(it)
            animateCameraToMarker(marker)
        }
        return true
    }

    private fun animateCameraToMarker(marker: Marker) {
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(marker.position, 15f)  // Adjust the zoom level as needed
        mMap.animateCamera(cameraUpdate)
    }

    private fun showDetails(location: Location) {
        val riskCategory = when (location.value as Double) {
            in 0.0..0.2 -> "Low Risk of Flooding"
            in 0.2..0.5 -> "Medium Risk of Flooding"
            in 0.5..0.8 -> "High Risk of Flooding"
            in 0.8..1.0 -> "Very High Risk of Flooding"
            else -> "Unknown Risk of Flooding"
        }

        val color = when (location.value) {
            in 0.0..0.2 -> "#34EA35"
            in 0.2..0.5 -> "#D5E933"
            in 0.5..0.8 -> "#E6D838"
            in 0.8..1.0 -> "#E93433"
            else -> "#808080"
        }

        val colorInt = Color.parseColor(color)

        val currentDateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        detailBinding.apply {
            locations.text = location.locationName
            header.text = riskCategory
            lastUpdated.text = getString(R.string.time_updated, currentDateTime)

            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_location)
            drawable?.let {
                DrawableCompat.setTint(it, colorInt)
                locationIcon.setImageDrawable(it)
            }
        }
        binding.detailContainer.removeAllViews()
        binding.detailContainer.addView(detailBinding.root)
        binding.detailContainer.visibility = View.VISIBLE
    }
}