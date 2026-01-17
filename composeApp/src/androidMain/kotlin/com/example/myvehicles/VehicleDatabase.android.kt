package com.example.myvehicles

import androidx.room.RoomDatabaseConstructor

// Στο Android, το Room παράγει την υλοποίηση.
// Αν το KSP αποτυγχάνει, δηλώνουμε το actual object έτσι:
@Suppress("NO_ACTUAL_FOR_EXPECT")
actual object AppDatabaseConstructor : RoomDatabaseConstructor<VehicleDatabase>