package ru.serget.myplacesonmap.viewmodal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel(){

    abstract fun handlerError(error: Throwable)
}