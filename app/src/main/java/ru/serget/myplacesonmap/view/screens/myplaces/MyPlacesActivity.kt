package ru.serget.myplacesonmap.view.screens.myplaces

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.serget.myplacesonmap.databinding.ActivityMyplecesBinding

class MyPlacesActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMyplecesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyplecesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}