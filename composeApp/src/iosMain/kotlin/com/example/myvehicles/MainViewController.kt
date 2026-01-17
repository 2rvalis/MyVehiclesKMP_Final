package com.example.myvehicles

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    val database = getDatabaseBuilder().build()
    val repository = VehicleRepository(database.vehicleDao())

    // Εδώ καλούμε τη συνάρτηση factory από το companion object
    // της VehicleViewModel, αντί να δημιουργούμε κλάση.
    val viewModelFactory = VehicleViewModel.factory(repository)

    App(viewModelFactory = viewModelFactory)
}