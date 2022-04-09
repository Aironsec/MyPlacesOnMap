package ru.serget.myplacesonmap.model.repository

import ru.serget.myplacesonmap.model.data.ItemPlace

interface IRepository<T> {
    fun getListMyPlaces(): T
    fun addMyPlace(place: ItemPlace)
}