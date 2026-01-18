package com.example.myvehicles

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.ConstructedBy
import androidx.room.RoomDatabaseConstructor
// ... το υπόλοιπο αρχείο
@Database(entities = [Vehicle::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}

// Αφαίρεσε το "override" αν υπάρχει, άφησέ το έτσι:
expect object AppDatabaseConstructor : RoomDatabaseConstructor<VehicleDatabase>