package ru.serget.myplacesonmap.model.repository

import ru.serget.myplacesonmap.model.data.ItemPlace
import ru.serget.myplacesonmap.model.datasourse.IDataSource

class RepositoryImpl (private val dataSource: IDataSource<List<ItemPlace>>): IRepository<List<ItemPlace>> {

    override fun getListLatLng(): List<ItemPlace> = dataSource.getListLanLng()
    override fun addLocation(place: ItemPlace) = dataSource.addPlace(place)

}