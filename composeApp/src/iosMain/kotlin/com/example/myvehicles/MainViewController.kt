package com.example.myvehicles

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    val database = getDatabaseBuilder().build()
    val repository = VehicleRepository(database.vehicleDao())

    // Χρήση του companion object factory
    val viewModelFactory = VehicleViewModel.factory(repository)

    App(viewModelFactory = viewModelFactory)
}