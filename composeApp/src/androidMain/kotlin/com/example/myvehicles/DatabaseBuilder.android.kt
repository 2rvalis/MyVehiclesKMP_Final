package com.example.myvehicles

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

// Χρησιμοποιούμε μια μεταβλητή για να κρατάμε το context
@SuppressLint("StaticFieldLeak")
lateinit var globalContext: Context

actual fun getDatabaseBuilder(): RoomDatabase.Builder<VehicleDatabase> {
    // Χρησιμοποιούμε το applicationContext για να αποφύγουμε Memory Leaks
    val context = globalContext.applicationContext
    val dbFile = context.getDatabasePath("vehicles.db")

    return Room.databaseBuilder<VehicleDatabase>(
        context = context,
        name = dbFile.absolutePath
    )
}