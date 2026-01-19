package com.example.myvehicles

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

// Η "υπόσχεση" που συνδέει Android και iOS
expect fun getDatabaseBuilder(): RoomDatabase.Builder<VehicleDatabase>

// Βοηθητική συνάρτηση για να παίρνουμε τη βάση
fun getDatabase(): VehicleDatabase {
    return getDatabaseBuilder()
        .setDriver(BundledSQLiteDriver()) // ΑΠΑΡΑΙΤΗΤΟ για να παίξει το iOS στο Appetize
        .build()
}