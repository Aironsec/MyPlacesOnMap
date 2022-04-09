package ru.serget.myplacesonmap.model.data

import com.google.android.gms.maps.model.LatLng

sealed class AppStateForMyPlace {
    data class Success (val data: LatLng): AppStateForMyPlace()
    data class Error (val error: Throwable): AppStateForMyPlace()
}
