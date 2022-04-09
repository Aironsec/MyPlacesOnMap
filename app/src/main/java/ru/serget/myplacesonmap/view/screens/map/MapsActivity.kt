package ru.serget.myplacesonmap.view.screens.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.serget.myplacesonmap.R
import ru.serget.myplacesonmap.databinding.ActivityMapsBinding
import ru.serget.myplacesonmap.model.data.AppState
import ru.serget.myplacesonmap.utils.PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
import ru.serget.myplacesonmap.view.BaseView
import ru.serget.myplacesonmap.view.screens.myplaces.MyPlacesActivity

class MapsActivity : BaseView<AppState>(), OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private var mMap: GoogleMap? = null

    private var locationPermissionGranted: Boolean = false

    override val model: MapViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initMapFragment()
        getLocationPermission()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun initViewModel() {
        model.subscribe().observe(this) {
            renderData(it)
        }
    }

    private fun initMapFragment() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap?.setOnMapClickListener(this)
//        showCurrentLocation()
    }

    override fun onMapClick(position: LatLng) {
        model.getLatLng(position)
    }

    @SuppressLint("MissingPermission")
    private fun showCurrentLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val lastKnownLocation: Location? = task.result
                        lastKnownLocation?.let {
                            model.getLatLng(LatLng(lastKnownLocation.latitude,
                                lastKnownLocation.longitude))
                        }

                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_map_location -> showCurrentLocation()

            R.id.menu_map_myplaces -> navigationTo(MyPlacesActivity::class.java)

            R.id.menu_map_add_place -> model.addPlace()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted = true
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        locationPermissionGranted = false
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    locationPermissionGranted = true
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                mMap?.let {
                    it.clear()
                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(appState.data, 16f))
                    it.addMarker(MarkerOptions().position(appState.data)) }

            }

            else -> {}
        }
    }
}