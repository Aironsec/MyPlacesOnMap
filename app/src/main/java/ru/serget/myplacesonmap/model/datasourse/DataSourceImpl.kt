package ru.serget.myplacesonmap.model.datasourse

import ru.serget.myplacesonmap.model.data.ItemPlace

class DataSourceImpl:IDataSource<List<ItemPlace>> {
    private var myPlacesList = ArrayList<ItemPlace>()

    override fun getListLanLng(): List<ItemPlace> = emptyList()
    override fun addPlace(place: ItemPlace) {
        myPlacesList.add(place)
    }
}