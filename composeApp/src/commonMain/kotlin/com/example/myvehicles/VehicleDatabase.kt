package com.example.myvehicles

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.ConstructedBy
import androidx.room.RoomDatabaseConstructor

@Database(entities = [Vehicle::class], version = 3, exportSchema = false)
@ConstructedBy(AppDatabaseConstructor::class)
// Προσθήκη κενού constructor για να βοηθήσουμε το generation
expect abstract class VehicleDatabase : RoomDatabase {
    abstract fun vehicleDao(): VehicleDao
}

// Marker interface για τον compiler
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<VehicleDatabase>