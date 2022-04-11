package ru.serget.myplacesonmap.view.screens.myplaces

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.serget.myplacesonmap.databinding.ActivityMyplecesBinding
import ru.serget.myplacesonmap.model.data.AppStateForMyPlace
import ru.serget.myplacesonmap.model.data.ItemPlace
import ru.serget.myplacesonmap.utils.BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
import ru.serget.myplacesonmap.view.BaseView

class MyPlacesActivity : BaseView<AppStateForMyPlace>() {
    private lateinit var binding: ActivityMyplecesBinding

    private val adapter: MyPlaceAdapter by lazy { MyPlaceAdapter(itemClickListener) }
    private var positionRecyclerView: Int? = null

    private val itemClickListener: MyPlaceAdapter.ItemClickListener =
        object : MyPlaceAdapter.ItemClickListener {
            override fun clickItem(itemPlace: ItemPlace, position: Int) {
                positionRecyclerView = position
                val editPlaceDialogFragment = EditPlaceDialogFragment.newInstance(itemPlace)
                editPlaceDialogFragment.setOnSaveClickListener(onSavePlaceListener)
                editPlaceDialogFragment.show(supportFragmentManager,
                    BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
            }
        }

    private val onSavePlaceListener: EditPlaceDialogFragment.OnSaveClickListener =
        object : EditPlaceDialogFragment.OnSaveClickListener {
            override fun onClick(titlePlace: String, descriptionPlace: String) {
                //todo сохранить в модель
            }

        }

    override val model: MyPlaceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyplecesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initView()
    }

    private fun initView() {
        binding.rvMyPlace.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvMyPlace.adapter = adapter
        model.getListMyPlace()
    }

    private fun initViewModel() {
        model.subscribe().observe(this) { renderData(it) }
    }

    override fun renderData(appState: AppStateForMyPlace) {
        when (appState) {
            is AppStateForMyPlace.Success -> {
                adapter.setData(appState.data)
            }
            else -> {}
        }
    }
}