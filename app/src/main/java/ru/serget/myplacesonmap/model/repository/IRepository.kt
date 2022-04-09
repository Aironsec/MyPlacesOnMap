package ru.serget.myplacesonmap.model.repository

import ru.serget.myplacesonmap.model.data.ItemPlace

interface IRepository<T> {
    fun getListLatLng(): T
    fun addLocation(place: ItemPlace)
}