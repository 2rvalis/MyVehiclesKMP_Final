package com.example.myvehicles

import com.example.myvehicles.Vehicle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class VehicleViewModel(private val repository: VehicleRepository) : ViewModel() {

    val allVehicles: Flow<List<Vehicle>> = repository.allVehicles

    fun insert(vehicle: Vehicle) = viewModelScope.launch {
        repository.insert(vehicle)
    }

    fun update(vehicle: Vehicle) = viewModelScope.launch {
        repository.update(vehicle)
    }

    fun delete(vehicle: Vehicle) = viewModelScope.launch {
        repository.delete(vehicle)
    }

    fun getVehicleById(id: Long): Flow<Vehicle?> {
        return repository.getVehicleById(id)
    }

    companion object {
        fun factory(repository: VehicleRepository) = viewModelFactory {
            initializer {
                VehicleViewModel(repository)
            }
        }
    }
}