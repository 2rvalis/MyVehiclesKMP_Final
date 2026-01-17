package com.example.myvehicles

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    // Δημιουργούμε το factory εδώ για να το περάσουμε στην App()
    val database = getDatabaseBuilder().build()
    val repository = VehicleRepository(database.vehicleDao())
    val factory = VehicleViewModelFactory(repository)

    App(viewModelFactory = factory)
}