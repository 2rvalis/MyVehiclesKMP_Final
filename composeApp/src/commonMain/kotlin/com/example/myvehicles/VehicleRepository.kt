package com.example.myvehicles

import kotlinx.coroutines.flow.Flow

class VehicleRepository(private val vehicleDao: VehicleDao) {

    // Επιστρέφει τη λίστα όλων των οχημάτων
    val allVehicles: Flow<List<Vehicle>> = vehicleDao.getAllVehicles()

    // ΔΙΟΡΘΩΣΗ: Αλλαγή από Int σε Long για να συμβαδίζει με το Vehicle Entity
    fun getVehicleById(id: Long): Flow<Vehicle?> {
        return vehicleDao.getVehicleById(id)
    }

    // Εισαγωγή νέου οχήματος
    suspend fun insert(vehicle: Vehicle) {
        vehicleDao.insert(vehicle)
    }

    // Ενημέρωση υπάρχοντος οχήματος
    suspend fun update(vehicle: Vehicle) {
        vehicleDao.update(vehicle)
    }

    // Διαγραφή οχήματος
    suspend fun delete(vehicle: Vehicle) {
        vehicleDao.delete(vehicle)
    }
}