package ru.serget.myplacesonmap.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.serget.myplacesonmap.model.datasourse.DataSourceImpl
import ru.serget.myplacesonmap.model.repository.RepositoryImpl
import ru.serget.myplacesonmap.view.screens.map.MapViewModel
import ru.serget.myplacesonmap.view.screens.myplaces.MyPlaceViewModel

val screens = module {
    viewModel { MapViewModel(get()) }
    viewModel { MyPlaceViewModel(get()) }
}

val data = module {
    single { RepositoryImpl(DataSourceImpl()) }
}