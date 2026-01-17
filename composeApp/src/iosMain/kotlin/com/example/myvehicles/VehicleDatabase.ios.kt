package com.example.myvehicles

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
actual object AppDatabaseConstructor : RoomDatabaseConstructor<VehicleDatabase>
// ΔΙΟΡΘΩΣΗ: Αφαιρέθηκε το actual override fun initialize()