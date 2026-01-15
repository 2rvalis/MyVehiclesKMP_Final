package com.example.myvehicles

import Vehicle
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Vehicle::class], version = 3, exportSchema = false)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}

// Αυτό χρειάζεται για να ξέρει η Room πώς να δημιουργήσει την υλοποίηση
interface DB {
    fun clearAllTables() {}
}