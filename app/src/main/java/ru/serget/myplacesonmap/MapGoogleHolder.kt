package ru.serget.myplacesonmap

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapGoogleHolder: IMapGoogle {
    private lateinit var mMap: GoogleMap

    override fun addMarker(latLng: LatLng, title: String?) {
        mMap.addMarker(MarkerOptions().position(latLng).title(title))
    }

    override fun moveCamera(latLng: LatLng) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}