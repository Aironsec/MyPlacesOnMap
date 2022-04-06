package ru.serget.myplacesonmap.viewmodal

interface IInteractor<T> {
    suspend fun getListLatLon(): T
}