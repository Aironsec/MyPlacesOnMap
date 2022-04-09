package ru.serget.myplacesonmap.model.repository

import ru.serget.myplacesonmap.model.data.ItemPlace
import ru.serget.myplacesonmap.model.datasourse.IDataSource

class RepositoryImpl (private val dataSource: IDataSource<List<ItemPlace>>): IRepository<List<ItemPlace>> {

    override fun getListMyPlaces(): List<ItemPlace> = dataSource.getListMyPlace()
    override fun addMyPlace(place: ItemPlace) = dataSource.addMyPlace(place)

}