package com.example.myvehicles

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
actual object VehicleDatabaseConstructor : RoomDatabaseConstructor<VehicleDatabase> {
    actual override fun initialize(): VehicleDatabase = throw NotImplementedError()
}

// ΤΕΛΙΚΟ ΤΕΣΤ ΓΙΑ ΤΟ GIT #@