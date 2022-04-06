package ru.serget.myplacesonmap.model.data

import com.google.android.gms.maps.model.LatLng

sealed class AppState {
    data class Success (val data: LatLng?): AppState()
    data class Error (val error: Throwable): AppState()
//    object currentLocation: AppState()
}
