package ru.serget.myplacesonmap.model.data

sealed class AppStateForMyPlace {
    data class Success (val data: List<ItemPlace>): AppStateForMyPlace()
    data class Error (val error: Throwable): AppStateForMyPlace()
}
