package ru.serget.myplacesonmap.model.datasourse

import ru.serget.myplacesonmap.model.data.ItemPlace

interface IDataSource<T> {
    fun getListLanLng(): T
    fun addPlace(place: ItemPlace)
}