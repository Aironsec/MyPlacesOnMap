package ru.serget.myplacesonmap.view.screens.myplaces

import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.serget.myplacesonmap.databinding.ActivityMyplecesBinding
import ru.serget.myplacesonmap.model.data.AppStateForMyPlace
import ru.serget.myplacesonmap.view.BaseView

class MyPlacesActivity:BaseView<AppStateForMyPlace>() {
    private lateinit var binding: ActivityMyplecesBinding

    override val model: MyPlaceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyplecesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
    }

    private fun initViewModel() {
        model.subscribe().observe(this) { renderData(it) }
    }

    override fun renderData(appState: AppStateForMyPlace) {
        when(appState) {
            is AppStateForMyPlace.Success -> {

            }
            else -> {}
        }
    }
}