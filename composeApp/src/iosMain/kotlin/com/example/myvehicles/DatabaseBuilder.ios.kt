package com.example.myvehicles

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual fun getDatabaseBuilder(): RoomDatabase.Builder<VehicleDatabase> {
    val dbFilePath = NSHomeDirectory() + "/my_vehicles.db"
    return Room.databaseBuilder<VehicleDatabase>(
        name = dbFilePath,
        factory = {  // Χρησιμοποιούμε το ανώνυμο factory που παράγει η Room
            VehicleDatabase::class.instantiateImpl()
        }
    ).setDriver(BundledSQLiteDriver())
}