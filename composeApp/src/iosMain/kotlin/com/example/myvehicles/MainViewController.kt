package com.example.myvehicles

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    // Δημιουργούμε το factory εδώ για το iOS
    val database = getDatabaseBuilder().build()
    val repository = VehicleRepository(database.vehicleDao())
    val factory = VehicleViewModelFactory(repository)

    App(viewModelFactory = factory)
}