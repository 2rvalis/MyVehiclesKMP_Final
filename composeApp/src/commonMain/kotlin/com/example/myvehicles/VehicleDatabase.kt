package com.example.myvehicles

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.ConstructedBy
import androidx.room.RoomDatabaseConstructor

@Database(entities = [Vehicle::class], version = 1)
// ΠΡΟΣΟΧΗ: Χρησιμοποιούμε το όνομα του object, ΧΩΡΙΣ backticks και ΧΩΡΙΣ .ios.kt
@ConstructedBy(AppDatabaseConstructor::class)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
}

//force action