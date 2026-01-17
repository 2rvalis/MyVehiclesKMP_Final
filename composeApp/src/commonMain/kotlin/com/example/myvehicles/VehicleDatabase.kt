package com.example.myvehicles

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.ConstructedBy
import androidx.room.RoomDatabaseConstructor

@Database(entities = [Vehicle::class], version = 3, exportSchema = false)
@ConstructedBy(VehicleDatabaseConstructor::class)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}

// Αυτό το αντικείμενο θα υλοποιηθεί αυτόματα από το KSP για κάθε πλατφόρμα
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object VehicleDatabaseConstructor : RoomDatabaseConstructor<VehicleDatabase>