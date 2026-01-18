package com.example.myvehicles

import androidx.room.RoomDatabaseConstructor

// ΑΦΑΙΡΕΣΕ το @ConstructedBy από εδώ
@Suppress("NO_ACTUAL_FOR_EXPECT")
actual object AppDatabaseConstructor : RoomDatabaseConstructor<VehicleDatabase>