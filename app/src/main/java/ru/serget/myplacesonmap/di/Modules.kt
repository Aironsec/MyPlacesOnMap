package ru.serget.myplacesonmap.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.serget.myplacesonmap.MapGoogleHolder
import ru.serget.myplacesonmap.view.screens.map.MapViewModel

val screens = module {
    viewModel { MapViewModel() }
    single { MapGoogleHolder() }
}