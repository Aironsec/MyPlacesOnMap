package ru.serget.myplacesonmap.viewmodal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.serget.myplacesonmap.model.data.AppStateForMap

abstract class BaseViewModel<T>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel(){

    abstract fun handlerError(error: Throwable)
}