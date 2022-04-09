package ru.serget.myplacesonmap.model.datasourse

import ru.serget.myplacesonmap.model.data.ItemPlace

class DataSourceImpl:IDataSource<List<ItemPlace>> {
    private var myPlacesList = ArrayList<ItemPlace>()

    override fun getListMyPlace(): List<ItemPlace> = myPlacesList
    override fun addMyPlace(place: ItemPlace) {
        myPlacesList.add(place)
    }
}