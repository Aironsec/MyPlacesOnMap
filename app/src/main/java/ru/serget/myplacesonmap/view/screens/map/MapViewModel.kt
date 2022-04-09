package ru.serget.myplacesonmap.view.screens.map

import androidx.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng
import ru.serget.myplacesonmap.model.data.AppState
import ru.serget.myplacesonmap.model.data.ItemPlace
import ru.serget.myplacesonmap.model.repository.RepositoryImpl
import ru.serget.myplacesonmap.viewmodal.BaseViewModel

class MapViewModel(private val repo: RepositoryImpl): BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    private var lastKnownLatLng: LatLng = LatLng(0.0, 0.0)

    fun subscribe(): LiveData<AppState> = liveDataForViewToObserve

    override fun handlerError(error: Throwable) =
        _mutableLiveData.postValue(AppState.Error(error))

    override fun getLatLng(locate: LatLng) {
        lastKnownLatLng = locate
        _mutableLiveData.value = AppState.Success(locate)
    }

    fun addPlace() {
        val place = ItemPlace(lastKnownLatLng)
        repo.addLocation(place)

    }

}