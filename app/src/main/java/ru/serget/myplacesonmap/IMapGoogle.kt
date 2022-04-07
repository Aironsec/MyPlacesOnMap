package ru.serget.myplacesonmap

import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

interface IMapGoogle: OnMapReadyCallback {
    fun addMarker(latLng: LatLng)
    fun moveCamera(latLng: LatLng)
}