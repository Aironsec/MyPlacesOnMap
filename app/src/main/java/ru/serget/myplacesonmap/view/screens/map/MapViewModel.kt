package ru.serget.myplacesonmap.view.screens.map

import androidx.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng
import ru.serget.myplacesonmap.model.data.AppStateForMap
import ru.serget.myplacesonmap.model.data.ItemPlace
import ru.serget.myplacesonmap.model.repository.RepositoryImpl
import ru.serget.myplacesonmap.viewmodal.BaseViewModel

class MapViewModel(private val repo: RepositoryImpl): BaseViewModel<AppStateForMap>() {

    private val liveDataForViewToObserve: LiveData<AppStateForMap> = _mutableLiveData
    private var lastKnownLatLng: LatLng = LatLng(0.0, 0.0)

    fun subscribe(): LiveData<AppStateForMap> = liveDataForViewToObserve

    override fun handlerError(error: Throwable) {
        _mutableLiveData.value = AppStateForMap.Error(error)
    }


    fun getPositionMap(position: LatLng) {
        lastKnownLatLng = position
        _mutableLiveData.value = AppStateForMap.Success(position)
    }

    fun addMyPlace() {
        val place = ItemPlace(lastKnownLatLng)
        repo.addMyPlace(place)

    }

}