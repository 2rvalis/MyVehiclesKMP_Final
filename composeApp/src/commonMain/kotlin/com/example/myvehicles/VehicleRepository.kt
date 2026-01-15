package com.example.myvehicles

import Vehicle
import kotlinx.coroutines.flow.Flow

// Ο Repository λειτουργεί ως ενδιάμεσος μεταξύ του DAO και του ViewModel
class VehicleRepository(private val vehicleDao: VehicleDao) {

    // Επιστρέφει τη λίστα όλων των οχημάτων
    val allVehicles: Flow<List<Vehicle>> = vehicleDao.getAllVehicles()

    // Αναζητά ένα συγκεκριμένο όχημα βάσει ID
    fun getVehicleById(id: Int): Flow<Vehicle?> {
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