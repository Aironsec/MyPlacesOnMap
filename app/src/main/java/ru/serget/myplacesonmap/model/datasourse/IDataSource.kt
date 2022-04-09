package ru.serget.myplacesonmap.model.datasourse

import ru.serget.myplacesonmap.model.data.ItemPlace

interface IDataSource<T> {
    fun getListMyPlace(): T
    fun addMyPlace(place: ItemPlace)
}