package com.example.myvehicles

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

actual fun getDatabaseBuilder(): RoomDatabase.Builder<VehicleDatabase> {
    val dbFilePath = NSHomeDirectory() + "/vehicles.db"
    return Room.databaseBuilder<VehicleDatabase>(
        name = dbFilePath,
        factory = { VehicleDatabase::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver())
}