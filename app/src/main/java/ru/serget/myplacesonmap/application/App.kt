package ru.serget.myplacesonmap.application

import android.app.Application
import org.koin.core.context.startKoin
import ru.serget.myplacesonmap.di.screens

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(screens)
        }
    }

}