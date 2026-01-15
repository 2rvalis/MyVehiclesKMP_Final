package com.example.myvehicles

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

// Χρησιμοποιούμε μια μεταβλητή για να κρατάμε το context
@SuppressLint("StaticFieldLeak")
lateinit var globalContext: Context

actual fun getDatabaseBuilder(): RoomDatabase.Builder<VehicleDatabase> { // <--- Εδώ
    val dbFile = globalContext.getDatabasePath("vehicles.db")
    return Room.databaseBuilder<VehicleDatabase>( // <--- Και εδώ
        context = globalContext.applicationContext,
        name = dbFile.absolutePath
    )
}