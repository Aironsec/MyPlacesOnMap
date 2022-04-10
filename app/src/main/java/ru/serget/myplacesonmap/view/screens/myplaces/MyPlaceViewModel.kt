package ru.serget.myplacesonmap.view.screens.myplaces

import androidx.lifecycle.LiveData
import ru.serget.myplacesonmap.model.data.AppStateForMyPlace
import ru.serget.myplacesonmap.model.repository.RepositoryImpl
import ru.serget.myplacesonmap.viewmodal.BaseViewModel

class MyPlaceViewModel(private val repo: RepositoryImpl) : BaseViewModel<AppStateForMyPlace>() {

    private val liveDataForViewToObserve: LiveData<AppStateForMyPlace> = _mutableLiveData

    fun subscribe(): LiveData<AppStateForMyPlace> = liveDataForViewToObserve

    override fun handlerError(error: Throwable) =
        _mutableLiveData.postValue(AppStateForMyPlace.Error(error))

    fun getListMyPlace() {
        val myPlaces = repo.getListMyPlaces()
        _mutableLiveData.value = AppStateForMyPlace.Success(myPlaces)
    }
}