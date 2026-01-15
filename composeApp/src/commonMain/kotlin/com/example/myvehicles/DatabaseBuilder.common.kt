package com.example.myvehicles

import androidx.room.RoomDatabase

// Η "υπόσχεση" που συνδέει Android και iOS
expect fun getDatabaseBuilder(): RoomDatabase.Builder<VehicleDatabase>

// Βοηθητική συνάρτηση για να παίρνουμε τη βάση
fun getDatabase(): VehicleDatabase {
    return getDatabaseBuilder().build()
}