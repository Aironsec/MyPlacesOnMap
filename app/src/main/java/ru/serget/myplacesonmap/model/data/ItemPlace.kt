package ru.serget.myplacesonmap.model.data

import com.google.android.gms.maps.model.LatLng

data class ItemPlace(
    val locate: LatLng,
    val title: String = "",
    val description: String = ""
)