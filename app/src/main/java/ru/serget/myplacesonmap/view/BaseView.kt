package ru.serget.myplacesonmap.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import ru.serget.myplacesonmap.model.data.AppState
import ru.serget.myplacesonmap.viewmodal.BaseViewModel

abstract class BaseView<T: AppState>: AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)

    fun navigationTo(cls: Class<*>) {
        Intent(this, cls).also {
            startActivity(it)
        }
    }

}