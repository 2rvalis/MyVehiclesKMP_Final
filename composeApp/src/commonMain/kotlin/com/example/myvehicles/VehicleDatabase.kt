package com.example.myvehicles

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

@Database(entities = [Vehicle::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class) // <--- ΜΟΝΟ ΕΔΩ
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}

// Η δήλωση του constructor
expect object AppDatabaseConstructor : RoomDatabaseConstructor<VehicleDatabase>