package com.example.myvehicles

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<VehicleDatabase> {
    // Ορίζουμε το μονοπάτι για το αρχείο της βάσης στο iOS
    val dbFilePath = NSHomeDirectory() + "/vehicles.db"

    return Room.databaseBuilder<VehicleDatabase>(
        name = dbFilePath,
        factory = { AppDatabaseConstructor.initialize() }
    )
}