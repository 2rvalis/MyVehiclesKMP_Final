package com.example.myvehicles

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.ConstructedBy
import androidx.room.RoomDatabaseConstructor

@ConstructedBy(AppDatabaseConstructor::class) // Μετακίνησέ το ΕΔΩ
@Database(entities = [Vehicle::class], version = 1)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}