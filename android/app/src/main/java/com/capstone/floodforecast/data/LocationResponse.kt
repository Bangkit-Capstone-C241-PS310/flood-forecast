package com.capstone.floodforecast.data

import com.google.gson.annotations.SerializedName

data class LocationResponse(

	@field:SerializedName("data")
	val data: List<Location?>? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Location(

	@field:SerializedName("val")
	val value: Any? = null,

	@field:SerializedName("location_name")
	val locationName: String? = null,

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("latitude")
	val lat: Double? = null,

	@field:SerializedName("longitude")
	val lon: Double? = null
)
