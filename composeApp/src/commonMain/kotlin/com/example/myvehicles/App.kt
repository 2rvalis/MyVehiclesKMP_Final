package com.example.myvehicles

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun App(viewModelFactory: ViewModelProvider.Factory) {
    MaterialTheme {
        val vehicleViewModel: VehicleViewModel = viewModel(factory = viewModelFactory)
        AppNavigation(viewModel = vehicleViewModel)
    }
}

@Composable
fun AppNavigation(viewModel: VehicleViewModel) {
    val navController = rememberNavController()
    val vehicles by viewModel.allVehicles.collectAsState(initial = emptyList())

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        // 1. Λίστα
        composable("list") {
            VehicleListScreen(
                vehicles = vehicles,
                onVehicleClick = { vehicleId ->
                    navController.navigate("details/$vehicleId")
                },
                onDeleteVehicle = { vehicle ->
                    viewModel.delete(vehicle)
                },
                onAddVehicle = {
                    navController.navigate("add")
                }
            )
        }

        // 2. Προσθήκη (Προσοχή στα ονόματα onSave/onCancel)
        composable("add") {
            AddVehicleScreen(
                onSave = { newVehicle ->
                    viewModel.insert(newVehicle)
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }

        // 3. Λεπτομέρειες
        composable("details/{vehicleId}") { backStackEntry ->
            val vehicleId = backStackEntry.arguments?.getString("vehicleId")?.toLongOrNull()
            val vehicle = vehicles.find { it.id == vehicleId }

            if (vehicle != null) {
                VehicleDetailScreen(
                    vehicle = vehicle,
                    onDelete = {
                        viewModel.delete(vehicle)
                        navController.popBackStack()
                    },
                    onUpdate = { updatedVehicle ->
                        viewModel.update(updatedVehicle)
                    },
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}