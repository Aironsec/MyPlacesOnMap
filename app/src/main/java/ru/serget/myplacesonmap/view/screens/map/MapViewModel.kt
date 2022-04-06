package ru.serget.myplacesonmap.view.screens.map

import androidx.lifecycle.LiveData
import com.google.android.gms.maps.model.LatLng
import ru.serget.myplacesonmap.model.data.AppState
import ru.serget.myplacesonmap.viewmodal.BaseViewModel

class MapViewModel: BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> = liveDataForViewToObserve

    override fun handlerError(error: Throwable) =
        _mutableLiveData.postValue(AppState.Error(error))

    override fun getListLatLon(locate: LatLng) {
        _mutableLiveData.value = AppState.Success(locate)
    }

    override fun onCleared() {
//        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}