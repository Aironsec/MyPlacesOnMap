package ru.serget.myplacesonmap.model.data

import com.google.android.gms.maps.model.LatLng

sealed class AppStateForMap {
    data class Success (val data: LatLng): AppStateForMap()
    data class Error (val error: Throwable): AppStateForMap()
}
